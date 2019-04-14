package net.zhenghao.zh.common.aspect;

import eu.bitwalker.useragentutils.UserAgent;
import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.context.BaseContextHandler;
import net.zhenghao.zh.common.dao.SysLogMapper;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.common.utils.IPUtils;
import net.zhenghao.zh.common.utils.JSONUtils;
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

import java.lang.reflect.Method;

/**
 * 系统日志,切面处理类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月30日 下午1:33:58
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
		if(sysLogAnnotation != null){
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
		String params = JSONUtils.objToString(args);
		sysLog.setParams(params);
		
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
		String params = JSONUtils.objToString(args);
		sysLog.setParams(params);

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
		if (result instanceof R) {
			R r = (R) result;
			int code = (int) r.get("code");
			if(code == 0) {
				//操作成功
				sysLog.setStatus(SystemConstant.StatusType.ENABLE.getValue());

				//响应时间：ms
				sysLog.setRemark("响应时间：" + time + "ms");
			} else {
				sysLog.setStatus(SystemConstant.StatusType.DISABLE.getValue());
				sysLog.setRemark(String.valueOf(r.get("msg")));
			}
		}
		//保存系统日志
		sysLogMapper.save(sysLog);
	}
	
}
