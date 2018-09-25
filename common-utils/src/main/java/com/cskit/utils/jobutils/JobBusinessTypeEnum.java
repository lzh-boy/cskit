package com.cskit.utils.jobutils;

/**
 * @author chen.li
 * @date on 2018/7/14 13:35
 * @description
 * @Copyright (c) 2018 上海商帆信息科技有限公司-版权所有
 */
public enum JobBusinessTypeEnum {

    ACTIVITY_TYPE_ACTIVITY(1,"营销活动"),
    ACTIVITY_TYPE_TASK(2,"营销任务"),
    ACTIVITY_TYPE_COUPON(3,"发券");

    private int code;
    private String message;

    JobBusinessTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static JobBusinessTypeEnum getJobBusinessTypeEnumByCode(int code){
        for(JobBusinessTypeEnum businessTypeEnum : JobBusinessTypeEnum.values()){
            if(code==businessTypeEnum.getCode()){
                return businessTypeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
