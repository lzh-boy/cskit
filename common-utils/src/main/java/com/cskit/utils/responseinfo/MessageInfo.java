package com.cskit.utils.responseinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Micro
 * @Title: 提示信息
 * @Package com.cskit.utils.responseinfo
 * @Description: ${todo}
 * @date 2018/6/19 19:12
 */
@ApiModel(value = "提示消息结构")
public class MessageInfo {

    private String msg="";
    private String key="";

    public MessageInfo() {
    }

    public MessageInfo(String msg, String key) {
        this.msg = msg;
        this.key = key;
    }

    @ApiModelProperty(value = "消息值")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @ApiModelProperty(value = "消息标识号")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
