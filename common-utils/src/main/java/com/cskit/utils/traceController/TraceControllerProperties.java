package com.cskit.utils.traceController;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Micro
 * @Title: 自动配置 跟踪controller调用信息
 * @Package com.cskit.utils.tokens
 * @Description: ${todo}
 * @date 2018/8/9 10:24
 */
@ConfigurationProperties(prefix = "cskit.trace.controller")
public class TraceControllerProperties {
	
	private boolean enabled = true;
	
	private String skipPattern = "";

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getSkipPattern() {
		return skipPattern;
	}

	public void setSkipPattern(String skipPattern) {
		this.skipPattern = skipPattern;
	}
	
}
