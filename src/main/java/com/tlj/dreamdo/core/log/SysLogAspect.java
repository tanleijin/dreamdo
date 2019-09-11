package com.tlj.dreamdo.core.log;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


/**
* 系统日志，切面处理类
* @author tanleijin
* @date 2019/9/6 15:58
*/
@Aspect
@Component
@ConditionalOnBean(SysLogListener.class)
public class SysLogAspect {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SysLogAspect.class);

    //每一个线程维护变量的副本
    private ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	@Pointcut("@annotation(com.tlj.dreamdo.core.log.SysLog)")
	public void logPointCut() {}
	
	/**
     * 注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
	    startTime.set(System.currentTimeMillis());
	    Object result = null;
	    ISysLogTemplate sysLog = new ISysLogTemplate();
	    try {
	        result = point.proceed();
        } catch (Exception e) {
            // @ControllerAdvice 的异常不处理
            sysLog.setExMsg(e.getMessage());
            sysLog.setExDetail(LogUtil.getStackTrace(e));
        }finally {
            long time = System.currentTimeMillis() - startTime.get();
            sysLog.setExecuteTime(time);
            
            genSysLogDetail(point, time , sysLog);
            applicationContext.publishEvent(new SysLogEvent(sysLog));
        }
		return result;
	}

	private void genSysLogDetail(ProceedingJoinPoint joinPoint, long time  , ISysLogTemplate sysLog) {

		//1.获取方法上的SysLog注解
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLog log = method.getAnnotation(SysLog.class);
		if(log != null){
		  	//注解上的描述
			sysLog.setDescription(log.value());
		}
		//2.获取类和方法名称
		String className = joinPoint.getTarget().getClass().getName();
		//请求的方法名
		String methodName = signature.getName();
		sysLog.setMethodName(className + "." + methodName + "()");

		//3.获取请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			//保存全部参数
			String params = new Gson().toJson(args);
			sysLog.setParams(params);
		}catch (Exception e){
		    LOGGER.info(e.getLocalizedMessage() ,e);
		}
		
		sysLog.setCreateTime(LocalDateTime.now());
		sysLog.setExecuteTime(time);
	}

	
}