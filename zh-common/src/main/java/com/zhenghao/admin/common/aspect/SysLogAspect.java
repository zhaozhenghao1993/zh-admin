package com.zhenghao.admin.common.aspect;

import com.zhenghao.admin.common.annotation.SysLog;
import com.zhenghao.admin.common.constant.SystemConstant;
import com.zhenghao.admin.common.context.BaseContextHandler;
import com.zhenghao.admin.common.dao.SysLogMapper;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.entity.SysLogEntity;
import com.zhenghao.admin.common.util.IPUtils;
import com.zhenghao.admin.common.util.JSONUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    private final SysLogMapper sysLogMapper;

    @Autowired
    public SysLogAspect(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    @Pointcut("@annotation(com.zhenghao.admin.common.annotation.SysLog)")
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
        //例:com.zhenghao.admin.common.controller.SysLogController
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
        setStatusRemark(sysLog, false, 0, "异常信息：" + ex.getMessage());

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
        //例:com.zhenghao.admin.common.controller.SysLogController
        String className = joinPoint.getTarget().getClass().getName();
        //例:batchRemoveAll
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = joinPoint.getArgs();
        List<BindingResult> bindingResultList = dealParams(args, sysLog);

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
            setStatusRemark(sysLog, r.isSuccess(), time, String.valueOf(r.getMsg()));
        } else {
            // 如果响应结果返回值 Result 为 null, 则说不定 响应结果在 BindingResult 中
            BindingResult bindingResult = bindingResultList.stream().filter(Errors::hasErrors).findAny().orElse(null);
            if (bindingResult == null) {
                setStatusRemark(sysLog, true, time, "");
            } else {
                setStatusRemark(sysLog, false, time, bindingResult.getFieldError().getDefaultMessage());
            }
        }
        //保存系统日志
        sysLogMapper.save(sysLog);
    }

    /**
     * 设置 操作日志状态和描述
     *
     * @param sysLog    SysLogEntity
     * @param isSuccess 操作是否成功
     * @param time      响应时间
     * @param message   如果失败的失败信息
     */
    private void setStatusRemark(SysLogEntity sysLog, boolean isSuccess, long time, String message) {
        if (StringUtils.isNotBlank(message) && message.length() > SystemConstant.DEFAULT_REMARK_STRING_MAX_LENGTH) {
            message = message.substring(0, SystemConstant.DEFAULT_REMARK_STRING_MAX_LENGTH) + "...";
        }
        if (isSuccess) {
            sysLog.setStatus(SystemConstant.StatusType.ENABLE.getValue());
            //响应时间：ms
            sysLog.setRemark("响应时间：" + time + "ms");
        } else {
            sysLog.setStatus(SystemConstant.StatusType.DISABLE.getValue());
            sysLog.setRemark(message);
        }
    }

    /**
     * 格式化参数 (arg1, arg2, arg3)
     *
     * @param args
     * @param sysLog
     * @return
     */
    private List<BindingResult> dealParams(Object[] args, SysLogEntity sysLog) {
        List<BindingResult> bindingResultList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof BindingResult) {
                bindingResultList.add((BindingResult) args[i]);
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
        return bindingResultList;
    }

}
