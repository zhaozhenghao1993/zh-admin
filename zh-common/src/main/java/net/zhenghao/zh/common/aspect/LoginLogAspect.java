package net.zhenghao.zh.common.aspect;

import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.context.BaseContextHandler;
import net.zhenghao.zh.common.dao.SysLogMapper;
import net.zhenghao.zh.common.entity.R;
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

import java.util.HashMap;
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
	
	
	@AfterReturning(pointcut = "execution(* net.zhenghao.zh.auth.controller.SysLoginController.login(..))", returning = "retValue")
	public void doAfterReturning(JoinPoint joinPoint, Object retValue) {
		SysLogEntity sysLog = new SysLogEntity();
		
		//日志类型
        sysLog.setType(SystemConstant.LogType.LOGIN.getValue());
        
        //ip地址
        sysLog.setIp(IPUtils.getIpAddr());

        //用户操作
        sysLog.setOperation("登录");
        
        //请求的方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSONUtils.objToString(args[0]);
        sysLog.setParams(params);
        
      //用户信息及操作结果
        R r = (R) retValue;
        int code = (int) r.get("code");
        if (code == 0) {
            //登录成功
            sysLog.setUserId(BaseContextHandler.getUserId());
            sysLog.setUsername(BaseContextHandler.getUsername());
            sysLog.setResult(SystemConstant.StatusType.ENABLE.getValue());
            sysLog.setRemark("登录成功");
        } else {
            //登录失败
            sysLog.setUserId(-1L);
            sysLog.setUsername(String.valueOf(args[0]));
            sysLog.setResult(SystemConstant.StatusType.DISABLE.getValue());
            sysLog.setRemark("登录失败：" + r.get("msg"));
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

        //ip地址
        sysLog.setIp(IPUtils.getIpAddr());

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
        sysLog.setResult(SystemConstant.StatusType.ENABLE.getValue());
        sysLog.setRemark("退出系统");

        sysLogMapper.save(sysLog);
	}
}
