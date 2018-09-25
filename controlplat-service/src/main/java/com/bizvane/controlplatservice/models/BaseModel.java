package com.bizvane.controlplatservice.models;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Micro
 * @Title: Model 基类
 * @Package com.bizvane.controlplatservice.models
 * @Description: 模型共用属性
 * @date 2018/7/20 20:20
 */
@Data
@NoArgsConstructor
public class BaseModel implements Serializable {
    private static final long serialVersionUID = -1403277873418166981L;
    //主键id
    private long id;
    //数据有效性：1=有效；0=无效
    private int valid;
    //说明/备注
    private String remark;
    //创建人id
    private int createUserId;
    //创建用户名称
    private String createUserName;
    //创建时间
    private Date createDate;
    //修改人id
    private int modifiedUserId;
    //修改人用户名称
    private String modifiedUserName;
    //修改时间
    private Date modifiedDate;
    //版本号
    private String version;
}