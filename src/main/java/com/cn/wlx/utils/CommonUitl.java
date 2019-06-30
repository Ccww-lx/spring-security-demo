package com.cn.wlx.utils;

/**
 * Created by Adminn on 2019/6/30.
 */
public class CommonUitl {
    /**
     *
     * 生成digit 位随机数
     * @param digit
     * @return
     */
    public static String generateRandomNumbers(int digit) {
        int count = 1;
        for (int i = 1; i < digit; i++) {
            count *= 10;
        }
        int randomNumber = (int) ((Math.random() * 9 + 1) * count);
        return String.valueOf(randomNumber);
    }
}
