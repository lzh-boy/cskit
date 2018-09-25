package com.bizvane.utils.commonutils;

import java.util.Random;

/**
 * @author Micro
 * @Title: Cookie操作
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

    public static String suiJi_Letter() {
        char suiJi = '1';
        char[] ziMu = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        boolean[] flag = new boolean[ziMu.length];
        String ReturnInfo = "";
        for (int i = 0; i < ziMu.length; i++) {
            int s;
            char r;
            do {
                r = ziMu[0];
                s = (int) (Math.random() * ziMu.length);
            } while (flag[s]);
            suiJi = ziMu[s];
            ReturnInfo = String.valueOf(suiJi) + String.valueOf(r);
            flag[s] = true;
        }
        return ReturnInfo;
    }
}
