package com.cskit.utils.commonutils;

import java.util.Random;

/**
 * @author Micro
 * @Title: 随机码生成
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/19 19:12
 */
public class RandomNumber {

    /**
     * 生成随机数
     *
     * @param strLength 随机数的长度
     * @return
     */
    public static String getFixLenthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        int pross = (int) ((1 + rm.nextDouble()) * Math.pow(10, strLength));
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

    public static String getRandomLetter() {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        boolean[] flag = new boolean[letters.length];
        String ReturnInfo = "";
        int s;
        char r;
        for (int i = 0; i < letters.length; i++) {
            do {
                r = letters[0];
                s = (int) (Math.random() * letters.length);
            } while (flag[s]);
            ReturnInfo = String.valueOf(letters[s]) + String.valueOf(r);
            flag[s] = true;
        }
        return ReturnInfo;
    }
}
