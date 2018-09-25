package com.cskit.utils;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import sun.java2d.pipe.AAShapePipe;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package com.bizvane.utils
 * @Description: ${todo}
 * @date 2018/6/27 20:30
 */
@ComponentScan(value = "com.bizvane.utils")
public class UtilsController {
    /**
     * 　　* @Description: ${todo}
     * 　　* @param ${tags}
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author Micro
     * 　　* @date 2018/7/4 19:49
     *
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer swaggerProperties() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        p.setIgnoreUnresolvablePlaceholders(true);
        return p;
    }
}
