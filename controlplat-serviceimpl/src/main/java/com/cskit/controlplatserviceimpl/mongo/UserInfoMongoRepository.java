package com.cskit.controlplatserviceimpl.mongo;

import com.cskit.controlplatservice.models.UserInfo;
import com.cskit.utils.mongoutils.MongoTemplateService;

/**
 * @author Micro
 * @Title: 用户信息处理
 * @Package com.cskit.controlplatserviceimpl.mongo
 * @Description: 用户信息在Mongo中的操作
 * @date 2018/7/4 16:20
 */
public interface UserInfoMongoRepository extends MongoTemplateService<UserInfo, Long> {
}
