package com.cskit.utils.constants;


/**
 * @author chen.li
 * @version 1.0
 * @date on 2018/6/21 10:30
 * @description 系统常量类，此处为系统级别常量定义，非系统级别请自己新增业务常量类
 * @Copyright (c) 2018 上海商帆信息科技有限公司-版权所有
 */
public class SysConstants {
    //无效
    public static final Integer INVALID = 0;
    //有效
    public static final Integer VALID = 1;
    /**
     * Token 名字
     */
    public static String TOKEN = "Token";


    /**
     * 随机数Cookie名字
     */
    public static final String RANDOM_NUMBER = "RandomNumber";


    /**
     * 域名称
     */
    public static String DOMAIN = "localhost";

    /**
     * Cookie存活时长
     */
    public static int COOKIE_LIFE_TIME = 60 * 60 * 24 * 7;

    /**
     * Token存活时长
     */
    public static Long TOKEN_LIFE_TIME = Long.valueOf(60 * 60 * 24 * 7);

    /**
     * 日期时间格式化模板
     */
    public static String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化模板
     */
    public static String DATE__FORMAT_PATTERN = "yyyy-MM-dd";

    /**
     * 时间格式化模板
     */
    public static String TIME__FORMAT_PATTERN = "HH:mm:ss";
}
