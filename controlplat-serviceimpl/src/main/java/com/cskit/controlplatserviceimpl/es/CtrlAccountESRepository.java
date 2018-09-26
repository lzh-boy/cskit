package com.cskit.controlplatserviceimpl.es;

import com.bizvane.controlplatservice.models.CtrlAccountModel;
import com.bizvane.controlplatservice.models.UserInfo;
import com.bizvane.utils.esutils.EsTemplateService;
import com.bizvane.utils.mongoutils.MongoTemplateService;

/**
 * @author Micro
 * @Title: 账号操作
 * @Package com.bizvane.controlplatserviceimpl.es
 * @Description: 用户信息在ES中的操作
 * @date 2018/7/23 16:20
 */
public interface CtrlAccountESRepository extends EsTemplateService<CtrlAccountModel, Long> {
}
