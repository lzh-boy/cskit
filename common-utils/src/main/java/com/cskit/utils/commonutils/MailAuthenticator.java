package com.cskit.utils.commonutils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author Micro
 * @Title: null
 * @Package com.cskit.utils.commonutils
 * @Description: null
 * @date 2018/6/26 20:01
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
