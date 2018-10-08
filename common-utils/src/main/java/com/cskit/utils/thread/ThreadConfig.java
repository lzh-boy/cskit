package com.cskit.utils.thread;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Micro
 * @Title: 线程池配置信息
 * @Package com.cskit.utils.thread
 * @Description: ${todo}
 * @date 2018/8/9 10:24
 */
@ConfigurationProperties(prefix = "thread.pool")
@Component
public class ThreadConfig {
  
  private int corePoolSize = 1;
  
  private int maxPoolSize = 8;
  
  private int queueCapacity = 2000; 
  
  private int keepAliveSeconds = 120;
  
  private String threadNamePrefix = "taskExecutor-";

  public int getCorePoolSize() {
    return corePoolSize;
  }

  public void setCorePoolSize(int corePoolSize) {
    this.corePoolSize = corePoolSize;
  }

  public int getMaxPoolSize() {
    return maxPoolSize;
  }

  public void setMaxPoolSize(int maxPoolSize) {
    this.maxPoolSize = maxPoolSize;
  }

  public int getQueueCapacity() {
    return queueCapacity;
  }

  public void setQueueCapacity(int queueCapacity) {
    this.queueCapacity = queueCapacity;
  }

  public int getKeepAliveSeconds() {
    return keepAliveSeconds;
  }

  public void setKeepAliveSeconds(int keepAliveSeconds) {
    this.keepAliveSeconds = keepAliveSeconds;
  }

  public String getThreadNamePrefix() {
    return threadNamePrefix;
  }

  public void setThreadNamePrefix(String threadNamePrefix) {
    this.threadNamePrefix = threadNamePrefix;
  }
  
  
}
