package com.cskit.utils.enumutils;

/**
 * @author chen.li
 * @date on 2018/7/12 19:33
 * @description job枚举类
 * @Copyright (c) 2018 上海商帆信息科技有限公司-版权所有
 */
public enum JobEnum {

    //路由策略
    EXECUTOR_ROUTE_STRATEGY_FIRST("FIRST","第一个"),
    EXECUTOR_ROUTE_STRATEGY_LAST("LAST","最后一个"),
    EXECUTOR_ROUTE_STRATEGY_ROUND("ROUND","轮询"),
    EXECUTOR_ROUTE_STRATEGY_RANDOM("RANDOM","随机"),
    EXECUTOR_ROUTE_STRATEGY_CONSISTENTHASH("CONSISTENT_HASH","一致性HASH"),
    EXECUTOR_ROUTE_STRATEGY_LFU("LEAST_FREQUENTLY_USED","最不经常使用"),
    EXECUTOR_ROUTE_STRATEGY_LRU("LEAST_RECENTLY_USED","最近最久未使用"),
    EXECUTOR_ROUTE_STRATEGY_FAILOVER("FAILOVER","故障转移"),
    EXECUTOR_ROUTE_STRATEGY_BUSYOVER("BUSYOVER","忙碌转移"),
    EXECUTOR_ROUTE_STRATEGY_SHARD("SHARDING_BROADCAST","分片广播"),

    //运行模式
    GLUE_TYPE_BEAN("BEAN", "BEAN"),
    GLUE_TYPE_GLUE_GROOVY("GLUE_GROOVY", "GLUE(JAVA)"),
    GLUE_TYPE_GLUE_SHELL("GLUE_SHELL", "GLUE(SHELL)"),
    GLUE_TYPE_GLUE_PYTHON("GLUE_PYTHON","GLUE(PYTHON)"),
    GLUE_TYPE_GLUE_NODEJS("GLUE_NODEJS", "GLUE(NODEJS)"),

    //JOB失败处理策略
    EXECUTOR_FAIL_STRATEGY_NULL("NULL","无"),
    EXECUTOR_FAIL_STRATEGY_TRIGGER_RETRY("FAIL_TRIGGER_RETRY","调度失败重试"),
    EXECUTOR_FAIL_STRATEGY_HANDLE_RETRY("FAIL_HANDLE_RETRY","执行失败重试"),

    //阻塞处理策略
    EXECUTOR_BLOCK_SERIAL_EXECUTION("SERIAL_EXECUTION","单机串行"),
    EXECUTOR_BLOCK_DISCARD_LATER("DISCARD_LATER","丢弃后续调度"),
    EXECUTOR_BLOCK_COVER_EARLY("COVER_EARLY","覆盖之前调度");

    JobEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String value;

    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
