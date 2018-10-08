package com.cskit.basecontroller;

import com.cskit.basecontroller.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;


@ComponentScan(value = "com.cskit.basecontroller")
public class BaseController {
    protected HttpServletRequest request;

    @Autowired
    protected RedisUtil redisUtil;

    @ModelAttribute
    public void InitBaseController(HttpServletRequest request) {
        this.request = request;
    }

//    protected String CurrentUser() {
//
//        request.
//        UserInfoDetail userInfoDetail = new UserInfoDetail();
//        String token = null;
//        token = CookieUtil.getCookie(String.class, request, ConstKeys.Token);
//        if (token == null) {
//            token = request.getParameter(ConstKeys.Token);
//        }
//        if (token != null) //PC
//        {
//            userInfoDetail = (UserInfoDetail) redisUtil.get(ConstKeys.Token.concat(token));
//        } else {
//            User user = new User();
//            user.setUserId(1);
//            user.setUserName("sysadmin");
//            user.setMobile("13918347839");
//            ModelMapper modelMapper = new ModelMapper();
//            modelMapper.addMappings(new UserToUserInfoDetailMapper());
//            userInfoDetail = modelMapper.map(user, UserInfoDetail.class);
//        }
//        userInfoDetail.setUserInfoID(1);
//        userInfoDetail.setCompanyID(1);
//        userInfoDetail.setDeptId(1);
//        return userInfoDetail;*/
//    }

/*    protected <T> ResultPageData<T> PageMapper(PageInfo<T> s) {
        ResultPageData<T> resultPageData = new ResultPageData<T>();
        resultPageData.getData().setPage(s.getPageNum());
        resultPageData.getData().setPageSize(s.getPageSize());
        resultPageData.getData().setTotal((int) s.getTotal());
        resultPageData.getData().setList(s.getList());
        return resultPageData;
    }*/
}
