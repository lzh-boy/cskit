package com.cskit.controlplatserviceimpl.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/1217:09
 */
@Component
public class DateTimeClient {
    private final RestTemplate restTemplate;

    /**
     * 　　* @Description: ${todo}
     * 　　* @param ${tags}
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author Micro
     * 　　* @date 2018/6/13 11:10
     */
    public DateTimeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDateTimeFromDateTimeService() {
        return restTemplate.getForObject("http://datetime-services", String.class);
    }
}
