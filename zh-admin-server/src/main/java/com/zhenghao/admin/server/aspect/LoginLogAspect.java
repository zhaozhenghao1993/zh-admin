package com.zhenghao.admin.server.aspect;

import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.enums.LogTypeEnum;
import com.zhenghao.admin.common.enums.StatusTypeEnum;
import com.zhenghao.admin.common.util.JSONUtils;
import com.zhenghao.admin.server.dao.SysLogMapper;
import com.zhenghao.admin.server.entity.SysLogEntity;
import com.zhenghao.admin.server.entity.dto.SysLoginDTO;
import com.zhenghao.admin.server.handler.context.RequestContextHandler;
import com.zhenghao.admin.server.util.IPUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 登陆登出日志,切面处理类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018年1月9日 下午12:19:36
 * LoginLogAspect.java
 */
@Aspect
@Component
public class LoginLogAspect {

    private final SysLogMapper sysLogMapper;

    @Autowired
    public LoginLogAspect(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    @AfterReturning(pointcut = "execution(* com.zhenghao.admin.server.controller.system.SysLoginController.login(..))", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        SysLogEntity sysLog = new SysLogEntity();

        //日志类型
        sysLog.setType(LogTypeEnum.LOGIN.getValue());

        //设置IP地址 浏览器 操作系统
        //设置IP地址 浏览器 操作系统
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            UserAgent userAgent = IPUtils.getUserAgent(servletRequestAttributes.getRequest());
            sysLog.setIp(IPUtils.getIpAddress(servletRequestAttributes.getRequest()));
            sysLog.setBrowser(userAgent.getBrowser().getName());
            sysLog.setOs(userAgent.getOperatingSystem().getName());
        }

        //用户操作
        sysLog.setOperation("登录");

        //请求的方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        SysLoginDTO sysLogin = (SysLoginDTO) args[0];
        ;
        sysLogin.setPassword("******");

        String params = JSONUtils.objToString(sysLogin);
        sysLog.setParams(params);

        //用户信息及操作结果
        Result r = (Result) result;
        if (r.isSuccess()) {
            //登录成功
            sysLog.setUsername(String.valueOf(sysLogin.getUsername()));
            sysLog.setStatus(StatusTypeEnum.ENABLE.getValue());
            sysLog.setRemark("登录成功");
        } else {
            //登录失败
            sysLog.setUserId(-1L);
            sysLog.setUsername(String.valueOf(sysLogin.getUsername()));
            sysLog.setStatus(StatusTypeEnum.DISABLE.getValue());
            sysLog.setRemark("登录失败：" + r.getMsg());
        }

        sysLogMapper.save(sysLog);
    }

    /**
     * 登出日志
     *
     * @param joinPoint
     */
    @Before("execution(* com.zhenghao.admin.server.controller.system.SysLoginController.logout(..))")
    public void doBefore(JoinPoint joinPoint) {
        SysLogEntity sysLog = new SysLogEntity();

        //日志类型
        sysLog.setType(LogTypeEnum.LOGIN.getValue());

        //设置IP地址 浏览器 操作系统
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            UserAgent userAgent = IPUtils.getUserAgent(servletRequestAttributes.getRequest());
            sysLog.setIp(IPUtils.getIpAddress(servletRequestAttributes.getRequest()));
            sysLog.setBrowser(userAgent.getBrowser().getName());
            sysLog.setOs(userAgent.getOperatingSystem().getName());
        }

        //用户操作
        sysLog.setOperation("退出登陆");

        //请求的方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //用户信息及操作结果
        sysLog.setUserId(RequestContextHandler.getUserId());
        sysLog.setUsername(RequestContextHandler.getUsername());
        sysLog.setStatus(StatusTypeEnum.ENABLE.getValue());
        sysLog.setRemark("退出系统");

        sysLogMapper.save(sysLog);
    }
}
