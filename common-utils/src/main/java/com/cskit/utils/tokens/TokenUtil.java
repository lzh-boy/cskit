package com.cskit.utils.tokens;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import io.jsonwebtoken.Claims;

/**
 * @author Micro
 * @Title: Token工具
 * @Package com.cskit.utils.tokens
 * @Description: ${todo}
 * @date 2018/8/9 10:24
 */
public class TokenUtil {
    public static final Logger logger= LoggerFactory.getLogger(TokenUtil.class);
    public  final   static  String CONTROL_TOKEN= "controlToken";
    public  final   static  String STAGE_TOKEN= "stageToken";

    public static SysAccountPO getStageUser(HttpServletRequest request) {
      String json = "";
      String sysAccountPoString = request.getHeader("sysAccountPo");
      
      if (StringUtils.isBlank(sysAccountPoString)) {
        logger.error("登录用户不存在！");
        return null;
      }
      try {
        json = URLDecoder.decode(sysAccountPoString, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } // 解码
      
      if (StringUtils.isBlank(json)) {
        logger.error("登录用户URLDecoder错误！");
        return null;
      } 

      SysAccountPO sysAccountPo = null;
      try {
        sysAccountPo = JSON.parseObject(json, SysAccountPO.class);
      } catch (Exception e) {
        logger.error("登录用户json转对象错误！");
      }

      return sysAccountPo;

    }

    public static CtrlAccountPO getControlUser(HttpServletRequest request) {
        String token = getToken(request,CONTROL_TOKEN);
        CtrlAccountPO ctrlAccountPO = null;
        try {
            //验证tocken是否存在用户信息
            // String token = request.getHeader("token");
            System.out.println("token--------------------------" + token);
            Claims claims = JWTUtil.parseJWT(token);
            String subject = claims.getSubject();
            System.out.println("subject-----" + subject);

            Gson gson = new Gson();
            ctrlAccountPO = gson.fromJson(subject, CtrlAccountPO.class);
        } catch (JsonSyntaxException e) {
            logger.info("获取中控token对象异常!");
            e.printStackTrace();
        } finally {

            return ctrlAccountPO;
        }

    }

    public static String getToken(HttpServletRequest request,String tokenName) {
        String token = "";
        try {
            token = Arrays.asList(request.getCookies()).stream().filter(obj -> tokenName.equals(obj.getName())).findFirst().get().getValue();

            logger.info(Arrays.toString(request.getCookies()));

        } catch (Exception e) {
            logger.info("获取Cookie中的token异常");
            e.printStackTrace();
        } finally {
            return token;
        }
    }

    public static Cookie getTokenCookie(HttpServletRequest request,String tokenName) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
          if (cookies.length > 0){
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(tokenName)) {
                    return cookies[i];
                }
            }
          }
        }
        return cookie;
    }
}
