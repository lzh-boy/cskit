package com.cskit.utils.enumutils;

/**
 * @author micro
 * @date on 2018/6/21 10:30
 * @description 系统响应返回码枚举类，此处定义所有该系统对外响应的返回码及释义
 * @version 1.0
 */
public enum SysResponseEnum {
    SUCCESS(0, "操作成功！"),
    FAILED(100, "操作失败！"),
    /**
     * >500 数据效验不通过
     */
    MODEL_FAILED_VALIDATION(500,"数据验证不通过"),

    OPERATE_FAILED_DATA_NOT_EXISTS(101, "查询数据不存在！"),
    OPERATE_FAILED_ADD_ERROR(102, "新增数据失败！"),
    OPERATE_FAILED_UPDATE_ERROR(103, "更新数据失败！"),
    OPERATE_FAILED_FILE_NOT_EXISTS(104, "操作失败，文件不存在！"),
    OPERATE_FAILED_FILE_UPLOAD_ERROR(105, "操作失败，文件上传错误！"),

    USER_LOGIN_FAILED_VALIDATECODE_NOT_EXISTS(106, "验证码不存在！"),
    USER_LOGIN_FAILED_VALIDATECODE_ERROR(107, "验证码错误！"),
    USER_LOGIN_SMS_CODE_OVERTIME(108, "短信验证码已过期！"),
    USER_LOGIN_FAILED_USERID_NOT_EXISTS(109, "登录失败，用户名不存在！"),
    USER_BIND_THIRD_LOGIN_ERROR(110, "登录失败，用户被禁用！"),
    USER_LOGIN_FAILED_USER_DISABLE(111, "登录失败，密码错误！"),
    USER_UPDATE_ERROR(112, "修改用户失败！"),
    USER_MIBILE_NOT_EXIST(113, "该手机号不存在！"),

    NETWORK_ERROR(114,"网络异常！"),
    SYSTEM_ERROR(115,"系统内部错误！"),

    UPLOAD_SUCCESS(116,"导入成功！"),
    UPLOAD_FAILED(117,"导入失败！");

    private int code;

    private String message;

    SysResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

} 

