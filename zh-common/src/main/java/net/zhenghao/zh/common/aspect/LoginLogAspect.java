package net.zhenghao.zh.common.aspect;

import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.common.entity.SysUserEntity;
import net.zhenghao.zh.common.manager.SysLogManager;
import net.zhenghao.zh.common.utils.IPUtils;
import net.zhenghao.zh.common.utils.JSONUtils;
import net.zhenghao.zh.common.utils.ShiroUtils;
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
	private SysLogManager sysLogManager;
	
	
	@AfterReturning(pointcut = "execution(* net.zhenghao.zh.shiro.controller.SysLoginController.login(..))", returning = "retValue")
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
        try{
            Map<String, Object> userMap = new HashMap<>(4);
            userMap.put("username", args[0]);
            userMap.put("password", args[1]);
            userMap.put("captcha", args[2]);
            String params = JSONUtils.beanToJson(userMap);
            sysLog.setParams(params);
        }catch (Exception e){

        }
        
      //用户信息及操作结果
        R r = (R) retValue;
        int code = (int) r.get("code");
        if (code == 0) {
            try {
                //登录成功
                SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
                sysLog.setUserId(sysUserEntity.getUserId());
                sysLog.setUsername(sysUserEntity.getUsername());
                sysLog.setResult(SystemConstant.StatusType.ENABLE.getValue());
                sysLog.setRemark("登录成功");
            } catch (Exception e) {
                sysLog.setUserId(-1L);
                sysLog.setUsername("获取用户信息为空");
                sysLog.setResult(SystemConstant.StatusType.DISABLE.getValue());
                sysLog.setRemark("登录：" + e.getMessage());
            }
        } else {
            //登录失败
            sysLog.setUserId(-1L);
            sysLog.setUsername(String.valueOf(args[0]));
            sysLog.setResult(SystemConstant.StatusType.DISABLE.getValue());
            sysLog.setRemark("登录失败：" + r.get("msg"));
        }

        sysLogManager.saveLog(sysLog);

        //登陆后发送邮件请求进入消息队列
        /*SysMailEntity mail = new SysMailEntity();
        mail.setSendPersonal("zh-security");
        mail.setReceiveMail("736720794@qq.com");
        mail.setReceivePersonal("赵正浩");
        mail.setSubject("zh-security登陆");
        String userInfoTemplate = "登陆信息如下:☕username:%s☕ip:%s☕param:%s☕remark:%s";
        String userInfoString = String.format(userInfoTemplate, sysLog.getUsername(), sysLog.getIp(), sysLog.getParams(), sysLog.getRemark());
        mail.setContent(userInfoString);
        KafkaMqMail.sendMsgToKafka(mail);*/
	}
	
	/**
	 * 登出日志
	 * @param joinPoint
	 */
	@Before("execution(* net.zhenghao.zh.shiro.controller.SysLoginController.logout(..))")
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
        try {
            SysUserEntity sysUserEntity = ShiroUtils.getUserEntity();
            sysLog.setUserId(sysUserEntity.getUserId());
            sysLog.setUsername(sysUserEntity.getUsername());
            sysLog.setResult(SystemConstant.StatusType.ENABLE.getValue());
            sysLog.setRemark("退出系统");
        } catch (Exception e) {
            sysLog.setUserId(-1L);
            sysLog.setUsername("获取用户信息为空");
            sysLog.setResult(SystemConstant.StatusType.DISABLE.getValue());
            sysLog.setRemark("退出系统：" + e.getMessage());
        }

        sysLogManager.saveLog(sysLog);
	}
}
