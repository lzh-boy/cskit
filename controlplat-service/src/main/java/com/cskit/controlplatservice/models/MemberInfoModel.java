package com.cskit.controlplatservice.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author Micro
 * @Title: 会员模型
 * @Package com.cskit.controlplatservice.models
 * @Description: 会员基本信息
 * @date 2018/7/20 20:24
 */
public class MemberInfoModel  extends BaseModel {
    private static final long serialVersionUID = -7718431512626549518L;
    @Id
    private long id;
    //会员code
    private String memberCode;
    //会员卡号
    private String cardNo;
    //会员姓名
    private String name;
    //会员手机号
    private String phone;
    //会员身份证
    private String idCard;
    //会员生日
    private String birthday;
    //会员省份
    private String province;
    //会员市区
    private String city;
    //会员县
    private String county;
    //详细地址
    private String address;
    //会员邮件
    private String email;
    //会员头像
    private String headPortraits;
    //会员体系id
    private long memberSysId;
    //企业id
    private long companyId;
    //品牌
    private long brandId;
    //微信Openid
    private String wxOpenId;
    //微信UnionId
    private String wxUnionId;
    //等级名称
    private String levelName;
    //标签id
    private String labelIds;
    //会员积分
    private int countIntegral;
    //开卡时间
    private Date openCardTime;
    //服务门店名称
    private Date serviceStoreName;
    //服务导购名称
    private Date serviceGuideName;
}
