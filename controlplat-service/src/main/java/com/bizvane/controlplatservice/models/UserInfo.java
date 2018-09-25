package com.bizvane.controlplatservice.models;

import io.swagger.annotations.ApiModel;

import javax.validation.Valid;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/14 14:58
 */
@ApiModel(value = "用户对象", description = "用户对象")
public class UserInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseraddr() {
        return useraddr;
    }

    public void setUseraddr(String useraddr) {
        this.useraddr = useraddr;
    }

    public int getUsersex() {
        return usersex;
    }

    public void setUsersex(int usersex) {
        this.usersex = usersex;
    }

    public java.sql.Timestamp getAdddatetime() {
        return adddatetime;
    }

    public void setAdddatetime(java.sql.Timestamp adddatetime) {
        this.adddatetime = adddatetime;
    }

    @io.swagger.annotations.ApiModelProperty(value = "标识符", name = "id", required = true, example = "10000")
    private long id;
    @io.swagger.annotations.ApiModelProperty(value = "用户名", name = "username", required = true, example = "Micro")
    private String username;
    @io.swagger.annotations.ApiModelProperty(value = "住址", name = "useraddr", required = true, example = "山东省定陶区")
    private String useraddr;
    @io.swagger.annotations.ApiModelProperty(value = "性别", name = "usersex", required = false, example = "1")
    private int usersex = 1;
    @io.swagger.annotations.ApiModelProperty(value = "添加时间", name = "adddatetime", required = false, example = "2018-06-15 14:00:05")
    private java.sql.Timestamp adddatetime = new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis());
}
