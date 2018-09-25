package com.bizvane.controlplatserviceimpl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/4 17:10
 */
@Component
@ConfigurationProperties(prefix = "spring.swagger", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class SwaggerConfig {
    public boolean getShowinfo() {
        return showinfo;
    }

    public void setShowinfo(boolean showinfo) {
        this.showinfo = showinfo;
    }

    private boolean showinfo = false;
}
