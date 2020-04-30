package com.qgh.spring_mvc.common.util;

/**
 * create by moziguo on 2019/12/10 17:01
 * 银行卡号校验
 * 校验过程：
 * 1.从卡号的最后一位数字开始，逆向将奇数位（1，3，5 等等相加）
 * 2.从卡号最后一位数字开始，逆向将偶数位数字，先乘以2，如果乘积为两位数，将个位数字相加，即将其减去9，再求和。
 * 3.将奇数位总和加上偶数位总和，结果应该可以被10 整
 */
public class BankUtils {

    /**
     * 校验银行卡卡号(比较算出的校验位和卡号里的校验位)
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 用不含校验位的银行卡卡号，采用 Luhm 校验算法获得校验位(卡号最后一位为校验位)
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            throw new IllegalArgumentException("Bank card code must be number!");
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    //测试银行卡号是否为真
    public static void main(String[] args) {
        boolean chenk1= checkBankCard("6217002020029471752");
        boolean chenk= checkBankCard("193204754093");
        System.out.println(chenk1);
        System.out.println(chenk);
    }
}
