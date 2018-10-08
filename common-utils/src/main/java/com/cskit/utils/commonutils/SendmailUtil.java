package com.cskit.utils.commonutils;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * @author Micro
 * @Title: Cookie操作
 * @Package com.cskit.utils.commonutils
 * @Description: ${todo}
 * @date 2018/6/19 19:12
 */
public class SendmailUtil {
    /**
     * 以html发送邮件(带附件)
     *
     * @param mailInfo 待发送的邮件的信息
     */
    public boolean sendHtmlAndAffixMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MailAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidateSSL()) {
            pro.put("mail.smtp.starttls.enable", "true");
            pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        // 如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MailAuthenticator(mailInfo.getUserName(),
                    mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = Session.getDefaultInstance(pro, authenticator);
        try {
            MimeMessage msg = new MimeMessage(session); // 构造MimeMessage 并设定基本的值
            // MimeMessage msg = new MimeMessage();
            msg.setFrom(new InternetAddress(mailInfo.getFromAddress()));
            // msg.addRecipients(Message.RecipientType.TO, address);
            // //这个只能是给一个人发送email
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailInfo.getToAddress()));
            msg.setSubject(MimeUtility.encodeText(mailInfo.getSubject()));
            Multipart mp = new MimeMultipart();// 构造Multipart
            MimeBodyPart mbpContent = new MimeBodyPart();// 向Multipart添加正文
            mbpContent.setContent(mailInfo.getContent(), "text/html;charset=utf-8");
            mp.addBodyPart(mbpContent);// 向MimeMessage添加（Multipart代表正文）
            Vector file = mailInfo.getAttachFileNames();
            Enumeration efile = file.elements();// 向Multipart添加附件
            while (efile.hasMoreElements()) {
                MimeBodyPart mbpFile = new MimeBodyPart();
                String filename = efile.nextElement().toString();
                System.out.println(filename.toLowerCase());
                FileDataSource fds = new FileDataSource(filename);
                mbpFile.setDataHandler(new DataHandler(fds));
                System.out.println(fds.getName());
                mbpFile.setFileName(MimeUtility.encodeText(fds.getName()));
                // 向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);
            }
            file.removeAllElements();
            // 向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());
            msg.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            transport.connect(mailInfo.getMailServerHost(), mailInfo
                    .getUserName(), mailInfo.getPassword());
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
        return true;

    }
}
