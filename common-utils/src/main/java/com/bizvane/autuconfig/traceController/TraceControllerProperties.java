package com.bizvane.autuconfig.traceController;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author wang.zeyan
 * @date 2018年9月3日
 *
 */
@ConfigurationProperties(prefix = "bizvane.trace.controller")
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
