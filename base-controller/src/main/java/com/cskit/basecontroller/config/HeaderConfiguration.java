package com.cskit.basecontroller.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Micro
 * @Title: 处理header
 * @Package com.cskit.basecontroller.config
 * @Description: 模块之间传递
 * @date 2018/7/25 19:21
 */
@Configuration
public class HeaderConfiguration {
    @Bean
    public RequestInterceptor headerInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null){
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    String name, values;
                    while (headerNames.hasMoreElements()) {
                        name = headerNames.nextElement();
                        values = request.getHeader(name);
                        requestTemplate.header(name, values);
                    }
                }
            }

        };
    }
}
