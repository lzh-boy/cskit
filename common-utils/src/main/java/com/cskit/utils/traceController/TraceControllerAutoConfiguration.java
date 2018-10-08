package com.cskit.utils.traceController;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @author Micro
 * @Title: 自动配置 跟踪controller调用信息
 * @Package com.cskit.utils.tokens
 * @Description: ${todo}
 * @date 2018/8/9 10:24
 */
@Configurable
@ConditionalOnWebApplication
@EnableConfigurationProperties(TraceControllerProperties.class)
public class TraceControllerAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(value = "cskit.trace.controller.enabled", matchIfMissing = true)
	public TraceControllerAspect tranceControllerAspect(ApplicationContext context, Environment env, TraceControllerProperties properties){
		return new TraceControllerAspect(context, env, Pattern.compile(properties.getSkipPattern()));
	}
}
