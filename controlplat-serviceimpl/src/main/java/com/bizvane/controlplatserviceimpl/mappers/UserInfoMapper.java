package com.bizvane.controlplatserviceimpl.mappers;


import com.bizvane.controlplatservice.models.UserInfo;
import com.bizvane.utils.tenant.TenantNotInterceptor;

import java.util.List;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/19 17:03
 */
public interface UserInfoMapper {
//    @TenantNotInterceptor
    public List<UserInfo> getAll();
}
