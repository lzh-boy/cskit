package com.cskit.utils.thread;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @author Micro
 * @Title: 注意：该线程池被所有的异步任务共享，而不属于某一个异步任务 描述：配置异步任务的线程池
 * @Package com.cskit.utils.thread
 * @Description: ${todo}
 * @date 2018/8/9 10:24
 */
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

  private final static Logger logger = LoggerFactory.getLogger(AsyncTaskExecutePool.class);

  @Autowired
  private ThreadConfig config;

  @Override
  public Executor getAsyncExecutor() {

    AsyncTaskExecutePool.logger.info("配置线程池");

    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(this.config.getCorePoolSize());
    executor.setMaxPoolSize(this.config.getMaxPoolSize());
    executor.setQueueCapacity(this.config.getQueueCapacity());
    executor.setKeepAliveSeconds(this.config.getKeepAliveSeconds());
    executor.setThreadNamePrefix(this.config.getThreadNamePrefix());

    // rejection-policy：当pool已经达到max size的时候，如何处理新任务
    // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {// 异步任务中异常处理
    return new AsyncUncaughtExceptionHandler() {

      @Override
      public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
        AsyncTaskExecutePool.logger.error("==========================" + arg0.getMessage() + "=======================",
            arg0);
        AsyncTaskExecutePool.logger.error("exception method:" + arg1.getName());
      }
    };
  }
}
