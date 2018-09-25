package com.cskit.utils.exception;

import com.bizvane.utils.mongoutils.MongoTemplateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/7/13 16:57
 */
@Component
public class FailedEventListenerException implements ApplicationListener<ApplicationFailedEvent> {
    private final static Logger logger = LoggerFactory.getLogger(FailedEventListenerException.class);
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        logger.error("系统启动失败......");
    }
}
