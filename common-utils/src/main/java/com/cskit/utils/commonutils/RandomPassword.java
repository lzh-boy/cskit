package com.cskit.utils.commonutils;

/**
 * @author Micro
 * @Title: 生成随机密码
 * @Package ${package_name}
 * @Description: com.cskit.utils.commonutils
 * @date 2018/6/19 19:12
 */
public class RandomPassword {
    public String getPassword() {
        String pfix = String.valueOf(RandomNumber.getRandomLetter()).toLowerCase();
        String strNumber = RandomNumber.getFixLenthString(4);
        String newPassword = pfix.concat(strNumber);
        return newPassword;
    }
}
