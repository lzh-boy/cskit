package com.cskit.autuconfig.traceController;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;

/**
 * 
 * @author wang.zeyan
 * @date 2018年9月3日
 *
 */
@Aspect
public class TraceControllerAspect implements Ordered {

	Logger logger = LoggerFactory.getLogger(TraceControllerAspect.class);

	protected final ApplicationContext context;
	
	protected final Environment env;
	
	protected final Pattern skipPattern;
	
	/**	切入点 注解 controller*/
	public final String ASPECT = "@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)";
	
	@Autowired
	HttpServletRequest request;
	
	@Around(ASPECT)
	public Object around(final ProceedingJoinPoint pjp) throws Throwable {
		
		String methodName = pjp.getSignature().getName();
		if(this.skipPattern.matcher(methodName).matches()){
			return pjp.proceed();
		}
		Stopwatch stopwatch = Stopwatch.createStarted();
		String applicationName = env.getProperty("spring.application.name");
		logger.info("应用名:{}", applicationName);
		logger.info("方法名:{}", methodName);
		logger.info("入参:{}", JSON.toJSONString(request.getParameterMap()));
		Object result = null;
		try {
			result = pjp.proceed();
			return result;
		}catch(Exception e){
			logger.info("异常:{}", e);
			throw e;
		}finally{
			stopwatch.stop();
			logger.info("出参:{}", JSON.toJSONString(result));
			logger.info("耗时:{}", stopwatch);
		}
	}

	public TraceControllerAspect(ApplicationContext context, Environment env, Pattern skipPattern) {
		super();
		this.context = context;
		this.env = env;
		this.skipPattern = skipPattern;
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}
