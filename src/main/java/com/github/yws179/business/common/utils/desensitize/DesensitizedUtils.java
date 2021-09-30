package com.github.yws179.business.common.utils.desensitize;


import org.springframework.util.StringUtils;

/**
 * 脱敏工具集
 * <a href="https://github.com/yws179/business-common-library/docs/Desensitize%20Utils.md">查看文档</a>
 * @author weisen.yan
 * @date 2021/7/23
 */
public class DesensitizedUtils {

    /**
     * 脱敏，使用默认的脱敏策略
     * <pre> DesensitizedUtil.desensitized("13700000000", DesensitizedType.MOBILE_PHONE)) = "137****0000"</pre>
     *
     * @param str              字符串
     * @param desensitizedType 脱敏类型;可以脱敏：手机号
     * @return 脱敏之后的字符串
     */
    public static String desensitized(String str, DesensitizedType desensitizedType) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        switch (desensitizedType) {
            case MOBILE_PHONE:
                return handleMobilePhone(str);
            case EMAIL:
                return handleEmail(str);
            default:
                return str;
        }
    }

    /**
     * 【手机号码】前三位，后4位，其他隐藏，比如135****2210
     *
     * @param num 移动电话
     * @return 脱敏后的移动电话
     */
    private static String handleMobilePhone(String num) {
        if (StringUtils.isEmpty(num) || num.length() < 11) {
            return num;
        }
        return new StringBuilder(num).replace(3, num.length() - 4, "****").toString();
    }

    /**
     * 【电子邮箱】邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示，比如：d**@126.com
     *
     * @param email 邮箱
     * @return 脱敏后的邮箱
     */
    public static String handleEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return email;
        }
        int index = email.indexOf('@');
        if (index <= 1) {
            return email;
        }
        return new StringBuilder(email).replace(1, index, "***").toString();
    }
}
