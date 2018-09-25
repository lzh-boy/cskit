package com.bizvane.utils.responseinfo;

import com.bizvane.utils.enumutils.SysResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Micro
 * @Title: 返回信息基类
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/19 19:12
 */
@ApiModel(value = "请求输出参数基类")
public class ResponseData<T> implements Serializable {

    private int code = SysResponseEnum.SUCCESS.getCode();
    private String message = SysResponseEnum.SUCCESS.getMessage();
    private T data;

    public ResponseData(T data) {
        this.data = data;
    }

    public ResponseData() {
    }

    @ApiModelProperty(value = "此编码只有0：成功，100：失败")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResponseData(int code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ResponseData(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseData(String message, T data) {
        this.data = data;
        this.message = message;
    }

    @ApiModelProperty(value = "返回数据")
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @ApiModelProperty(value = "提示消息")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
