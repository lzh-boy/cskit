package com.cskit.utils.commonutils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * ***************************************************************************
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2016/11/21
 * 实现功能 :
 * 作者 : zb
 * 版本 : v0.0.1
 * -----------------------------------------------------------------------------
 * 修改记录:
 * 日 期                版本     修改人      修改内容
 * 2016/11/21             v0.0.1     Micro        创建
 * ***************************************************************************
 */
public class MailAuthenticator extends Authenticator {

    private String userName;
    private String password;

    public MailAuthenticator() {
    }

    public MailAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
