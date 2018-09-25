package com.bizvane.utils.kafkautils;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/8/9 10:24
 */
@ConditionalOnProperty(name = "spring.kafka.consumer.group-id")
@Component
@ConfigurationProperties(prefix = "spring.kafka.consumer", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class ConsumerConfig implements Serializable {
    private static final long serialVersionUID = 5231134212346077681L;

    public String getBootstrapservers() {
        return bootstrapservers;
    }

    public void setBootstrapservers(String bootstrapservers) {
        this.bootstrapservers = bootstrapservers;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getEnableautocommit() {
        return enableautocommit;
    }

    public void setEnableautocommit(String enableautocommit) {
        this.enableautocommit = enableautocommit;
    }

    public String getAutooffsetreset() {
        return autooffsetreset;
    }

    public void setAutooffsetreset(String autooffsetreset) {
        this.autooffsetreset = autooffsetreset;
    }

    public String getKeydeserializer() {
        return keydeserializer;
    }

    public void setKeydeserializer(String keydeserializer) {
        this.keydeserializer = keydeserializer;
    }

    public String getValuedeserializer() {
        return valuedeserializer;
    }

    public void setValuedeserializer(String valuedeserializer) {
        this.valuedeserializer = valuedeserializer;
    }

    private String bootstrapservers;
    private String groupid;
    private String enableautocommit;
    private String autooffsetreset;
    private String keydeserializer;
    private String valuedeserializer;

    public Integer getSessiontimeout() {
        return sessiontimeout;
    }

    public void setSessiontimeout(Integer sessiontimeout) {
        this.sessiontimeout = sessiontimeout;
    }

    public Integer getRequesttimeout() {
        return requesttimeout;
    }

    public void setRequesttimeout(Integer requesttimeout) {
        this.requesttimeout = requesttimeout;
    }

    public Duration getHeartbeatinterval() {
        return heartbeatinterval;
    }

    public void setHeartbeatinterval(Duration heartbeatinterval) {
        this.heartbeatinterval = heartbeatinterval;
    }

    private Integer sessiontimeout;
    private Integer requesttimeout;
    private Duration heartbeatinterval;

    public Integer getMaxpartitionfetchbytes() {
        return maxpartitionfetchbytes;
    }

    public void setMaxpartitionfetchbytes(Integer maxpartitionfetchbytes) {
        this.maxpartitionfetchbytes = maxpartitionfetchbytes;
    }

    private Integer maxpartitionfetchbytes;

}
