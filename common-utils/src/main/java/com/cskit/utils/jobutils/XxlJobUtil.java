package com.cskit.utils.jobutils;

import com.cskit.utils.enumutils.JobEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author micro
 * @date on 2018/7/12 17:44
 * @description
 */
@Component
public class XxlJobUtil {

    @Autowired
    private JobClient jobClient;

    /**
     * 通用job添加方法
     * @param execuDate
     * @param desc
     * @param param
     * @param author
     * @param jobHandler
     * @param businessType
     */
    public void addJob(Date execuDate,String desc,String param,String author,String jobHandler,int businessType,String bizCode,String appName){
        //构建job对象
        XxlJobInfo xxlJobInfo = new XxlJobInfo();
        //设置appName
        xxlJobInfo.setAppName(appName);
        //设置路由策略
        xxlJobInfo.setExecutorRouteStrategy(JobEnum.EXECUTOR_ROUTE_STRATEGY_FIRST.getValue());
        //设置job定时器
        xxlJobInfo.setJobCron(getCronExpression(execuDate));
        //设置运行模式
        xxlJobInfo.setGlueType(JobEnum.GLUE_TYPE_BEAN.getValue());
        //设置job处理器
        xxlJobInfo.setExecutorHandler(jobHandler);
        //设置job描述
        xxlJobInfo.setJobDesc(desc);
        //设置执行参数
        xxlJobInfo.setExecutorParam(param);
        //设置阻塞处理策略
        xxlJobInfo.setExecutorBlockStrategy(JobEnum.EXECUTOR_BLOCK_SERIAL_EXECUTION.getValue());
        //设置失败处理策略
        xxlJobInfo.setExecutorFailStrategy(JobEnum.EXECUTOR_FAIL_STRATEGY_NULL.getValue());
        //设置负责人
        xxlJobInfo.setAuthor(author);
        //设置业务类型
        xxlJobInfo.setBizType(businessType);
        //设置任务或活动的Code
        xxlJobInfo.setBizCode(bizCode);
        //添加job
        jobClient.addJob(xxlJobInfo);
    }

    /**
     * 通用job添加方法
     * @param execuDateCron
     * @param desc
     * @param param
     * @param author
     * @param jobHandler
     * @param businessType
     */
    public void addJob(String execuDateCron,String desc,String param,String author,String jobHandler,int businessType,String bizCode,String appName){
        //构建job对象
        XxlJobInfo xxlJobInfo = new XxlJobInfo();
        //设置appName
        xxlJobInfo.setAppName(appName);
        //设置路由策略
        xxlJobInfo.setExecutorRouteStrategy(JobEnum.EXECUTOR_ROUTE_STRATEGY_FIRST.getValue());
        //设置job定时器
        xxlJobInfo.setJobCron(execuDateCron);
        //设置运行模式
        xxlJobInfo.setGlueType(JobEnum.GLUE_TYPE_BEAN.getValue());
        //设置job处理器
        xxlJobInfo.setExecutorHandler(jobHandler);
        //设置job描述
        xxlJobInfo.setJobDesc(desc);
        //设置执行参数
        xxlJobInfo.setExecutorParam(param);
        //设置阻塞处理策略
        xxlJobInfo.setExecutorBlockStrategy(JobEnum.EXECUTOR_BLOCK_SERIAL_EXECUTION.getValue());
        //设置失败处理策略
        xxlJobInfo.setExecutorFailStrategy(JobEnum.EXECUTOR_FAIL_STRATEGY_NULL.getValue());
        //设置负责人
        xxlJobInfo.setAuthor(author);
        //设置业务类型
        xxlJobInfo.setBizType(businessType);
        //设置任务或活动的Code
        xxlJobInfo.setBizCode(bizCode);
        //添加job
        jobClient.addJob(xxlJobInfo);
    }


    /**
     * 通用job移除方法
     * @param bizCode
     * @param businessType
     */
    public void removeByBiz(int businessType,String bizCode){
        //构建job对象
        XxlJobInfo xxlJobInfo = new XxlJobInfo();
        //设置业务类型
        xxlJobInfo.setBizType(businessType);
        //设置任务或活动的Code
        xxlJobInfo.setBizCode(bizCode);
        //添加job
        jobClient.removeByBiz(xxlJobInfo);
    }

    /**
     * 获取cron表达式
     * @param date
     * @return
     */
    public static String getCronExpression(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    /**
     * 格式化时间
     * @param date
     * @param dateFormat
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

}
