package com.cskit.utils.commonutils;

/**
 * @author Micro
 * @Title: Cookie操作
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/19 19:12
 */
public class RandomPassword {
    public String getPassword() {
        String pfix = String.valueOf(RandomNumber.suiJi_Letter()).toLowerCase();
        String str_num = RandomNumber.getFixLenthString(4);
        String newPassword = pfix + str_num;
        return newPassword;
    }
}
