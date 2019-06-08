package net.zhenghao.zh.common.aspect;

import eu.bitwalker.useragentutils.UserAgent;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.context.BaseContextHandler;
import net.zhenghao.zh.common.dao.SysLogMapper;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.common.utils.IPUtils;
import net.zhenghao.zh.common.utils.JSONUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 登陆登出日志,切面处理类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2018年1月9日 下午12:19:36
 * LoginLogAspect.java
 */
@Aspect
@Component
public class LoginLogAspect {

	@Autowired
    private SysLogMapper sysLogMapper;

	@AfterReturning(pointcut = "execution(* net.zhenghao.zh.auth.controller.SysLoginController.login(..))", returning = "result")
	public void doAfterReturning(JoinPoint joinPoint, Object result) {
		SysLogEntity sysLog = new SysLogEntity();
		
		//日志类型
        sysLog.setType(SystemConstant.LogType.LOGIN.getValue());

        //设置IP地址 浏览器 操作系统
        UserAgent userAgent = IPUtils.getUserAgent();
        sysLog.setIp(IPUtils.getIpAddr());
        sysLog.setBrowser(userAgent.getBrowser().getName());
        sysLog.setOs(userAgent.getOperatingSystem().getName());

        //用户操作
        sysLog.setOperation("登录");
        
        //请求的方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        
        //请求的参数
        Object[] args = joinPoint.getArgs();
        Map<String, Object> userMap = (Map<String, Object>) args[0];
        if (userMap.containsKey("password")) {
            userMap.put("password", "******");
        }
        String params = JSONUtils.objToString(userMap);
        sysLog.setParams(params);
        
      //用户信息及操作结果
        Result r = (Result) result;
        if (r.isSuccess()) {
            //登录成功
            sysLog.setUsername(String.valueOf(userMap.get("username")));
            sysLog.setStatus(SystemConstant.StatusType.ENABLE.getValue());
            sysLog.setRemark("登录成功");
        } else {
            //登录失败
            sysLog.setUserId(-1L);
            sysLog.setUsername(String.valueOf(userMap.get("username")));
            sysLog.setStatus(SystemConstant.StatusType.DISABLE.getValue());
            sysLog.setRemark("登录失败：" + r.getMsg());
        }

        sysLogMapper.save(sysLog);
	}
	
	/**
	 * 登出日志
	 * @param joinPoint
	 */
	@Before("execution(* net.zhenghao.zh.auth.controller.SysLoginController.logout(..))")
	public void doBefore(JoinPoint joinPoint) {
		SysLogEntity sysLog = new SysLogEntity();
		
		//日志类型
        sysLog.setType(SystemConstant.LogType.LOGIN.getValue());

        //设置IP地址 浏览器 操作系统
        UserAgent userAgent = IPUtils.getUserAgent();
        sysLog.setIp(IPUtils.getIpAddr());
        sysLog.setBrowser(userAgent.getBrowser().getName());
        sysLog.setOs(userAgent.getOperatingSystem().getName());

        //用户操作
        sysLog.setOperation("退出登陆");
        
        //请求的方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        
        //用户信息及操作结果
        sysLog.setUserId(BaseContextHandler.getUserId());
        sysLog.setUsername(BaseContextHandler.getUsername());
        sysLog.setStatus(SystemConstant.StatusType.ENABLE.getValue());
        sysLog.setRemark("退出系统");

        sysLogMapper.save(sysLog);
	}
}
