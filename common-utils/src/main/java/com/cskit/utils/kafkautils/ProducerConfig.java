package com.cskit.utils.kafkautils;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/8/9 10:23
 */
@ConditionalOnProperty(name = "spring.kafka.producer.client-id")
@Component
@ConfigurationProperties(prefix = "spring.kafka.producer", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class ProducerConfig {

    private static final long serialVersionUID = 5231134212346077681L;
    private String bootstrapservers;
    private String clientid;
    private String keyserializer;
    private String valueserializer;

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    private String acks;

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    private Integer retries;

    public Integer getBatchsize() {
        return batchsize;
    }

    public void setBatchsize(Integer batchsize) {
        this.batchsize = batchsize;
    }

    public Long getBuffermemory() {
        return buffermemory;
    }

    public void setBuffermemory(Long buffermemory) {
        this.buffermemory = buffermemory;
    }

    private Integer batchsize;
    private Long buffermemory;

    public String getBootstrapservers() {
        return bootstrapservers;
    }

    public void setBootstrapservers(String bootstrapservers) {
        this.bootstrapservers = bootstrapservers;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getKeyserializer() {
        return keyserializer;
    }

    public void setKeyserializer(String keyserializer) {
        this.keyserializer = keyserializer;
    }

    public String getValueserializer() {
        return valueserializer;
    }

    public void setValueserializer(String valueserializer) {
        this.valueserializer = valueserializer;
    }
}
