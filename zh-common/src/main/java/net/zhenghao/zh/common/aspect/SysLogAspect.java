package net.zhenghao.zh.common.aspect;

import eu.bitwalker.useragentutils.UserAgent;
import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.context.BaseContextHandler;
import net.zhenghao.zh.common.dao.SysLogMapper;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.common.util.IPUtils;
import net.zhenghao.zh.common.util.JSONUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 系统日志,切面处理类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月30日 下午1:33:58
 * SysLogAspect.java
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Pointcut("@annotation(net.zhenghao.zh.common.annotation.SysLog)")
    public void logPointCut() {
    }

    /**
     * 操作异常日志
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();

        sysLog.setType(SystemConstant.LogType.ERROR.getValue());

        SysLog sysLogAnnotation = method.getAnnotation(SysLog.class);
        if (sysLogAnnotation != null) {
            //注解上的描述
            sysLog.setOperation(sysLogAnnotation.value());
        }
        //请求的方法名
        //例:net.zhenghao.zh.common.controller.SysLogController
        String className = joinPoint.getTarget().getClass().getName();
        //例:batchRemoveAll
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = joinPoint.getArgs();
        dealParams(args, sysLog);

        //设置IP地址 浏览器 操作系统
        UserAgent userAgent = IPUtils.getUserAgent();
        sysLog.setIp(IPUtils.getIpAddr());
        sysLog.setBrowser(userAgent.getBrowser().getName());
        sysLog.setOs(userAgent.getOperatingSystem().getName());

        //用户名
        sysLog.setUserId(BaseContextHandler.getUserId());
        sysLog.setUsername(BaseContextHandler.getUsername());

        //操作状态和结果
        sysLog.setStatus(SystemConstant.StatusType.DISABLE.getValue());
        sysLog.setRemark("异常信息：" + ex);

        sysLogMapper.save(sysLog);
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        saveSysLog(point, time, result);
        return result;
    }


    private void saveSysLog(ProceedingJoinPoint joinPoint, long time, Object result) {
        //反射获取一个方法中的参数名（不是类型）
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //获取注解上面的描述,set到sysLog类中
            sysLog.setOperation(syslog.value());
            sysLog.setType(syslog.type().getValue());
        }
        //请求的方法名
        //例:net.zhenghao.zh.common.controller.SysLogController
        String className = joinPoint.getTarget().getClass().getName();
        //例:batchRemoveAll
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = joinPoint.getArgs();
        dealParams(args, sysLog);

        //设置IP地址 浏览器 操作系统
        UserAgent userAgent = IPUtils.getUserAgent();
        sysLog.setIp(IPUtils.getIpAddr());
        sysLog.setBrowser(userAgent.getBrowser().getName());
        sysLog.setOs(userAgent.getOperatingSystem().getName());

        //用户名
        sysLog.setUserId(BaseContextHandler.getUserId());
        sysLog.setUsername(BaseContextHandler.getUsername());

        sysLog.setTime(time);

        //操作执行状态
        if (result instanceof Result) {
            Result r = (Result) result;
            if (r.isSuccess()) {
                //操作成功
                sysLog.setStatus(SystemConstant.StatusType.ENABLE.getValue());

                //响应时间：ms
                sysLog.setRemark("响应时间：" + time + "ms");
            } else {
                sysLog.setStatus(SystemConstant.StatusType.DISABLE.getValue());
                sysLog.setRemark(String.valueOf(r.getMsg()));
            }
        } else {
            // 如果响应结果返回值 Result 为 null, 则说不定 响应结果在 BindingResult 中
            BindingResult results = sysLog.getResult();
            if (results != null) {
                if (results.hasErrors()) {
                    sysLog.setStatus(SystemConstant.StatusType.DISABLE.getValue());
                    sysLog.setRemark(String.valueOf(results.getFieldError().getDefaultMessage()));
                }
            }
        }
        //保存系统日志
        sysLogMapper.save(sysLog);
    }

    /**
     * 格式化参数 (arg1, arg2, arg3)
     *
     * @param args
     * @param sysLog
     * @return
     */
    private void dealParams(Object[] args, SysLogEntity sysLog) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof BindingResult) {
                sysLog.setResult((BindingResult) args[i]);
                sb.append("BindingResult");
            } else if (args[i] instanceof HttpServletResponse) {
                sb.append("HttpServletResponse");
            } else {
                sb.append(JSONUtils.objToString(args[i]));
            }
            if (i < args.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        sysLog.setParams(sb.toString());
    }

}
