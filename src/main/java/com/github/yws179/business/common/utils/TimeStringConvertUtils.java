package com.github.yws179.business.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间汉字描述转阿拉伯数字分钟（支持汉字描述小于100的数值）
 * 如：一个小时十五分钟 ==> 75分钟
 * 如：九十九小时十五分钟和一个半小时五分钟和5小时和4.5分和1.25小时 ==> 5955.0分钟和95.0分钟和300.0分钟和4.5分钟和75.0分钟
 * @author yws
 * @date 2019/10/18
 */
@Slf4j
public class TimeStringConvertUtils {

    private final static Pattern PATTERN = Pattern.compile("(?:(?:(?<HOUR>[零壹贰叁肆伍陆柒捌玖拾一二三四五六七八九十俩半.\\d]+)(?:个)?(?<HALF>半)?(?:小时|钟头|刻钟))|(?:(?<MINUTE>[零壹贰叁肆伍陆柒捌玖拾一二三四五六七八九十俩.\\d]+)分钟?))+");

    private final static char[] NUM_CN = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };

    private final static char[] NUM_TC = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};

    private final static char TEN_CN = '十';

    private final static char TEN_TC = '拾';

    private final static char HALF_CN = '半';

    private final static char DECIMAL_DOT = '.';

    private static Map<Character, Integer> numMap = new HashMap<>();

    static {
        for (int i = 0; i < NUM_CN.length; i++) {
            numMap.put(NUM_CN[i], i);
        }
    }

    /**
     * 前置处理：转换多种表述方式为单一表述方式，如大写转小写
     */
    public static String preProcess(String txt) {
        String result = txt;
        for (int i = 0; i < NUM_TC.length; i++) {
            result = result.replace(NUM_TC[i], NUM_CN[i]);
        }
        return result.replace('俩', NUM_CN[2]).replace(TEN_TC, TEN_CN);
    }

    /**
     * 汉字数字转数字（目前支持小数，不支持大于100的数值）
     */
    public static float toNum(String txt) {
        if (StringUtils.isEmpty(txt)) {
            return 0;
        } else if (String.valueOf(HALF_CN).equals(txt)) {
            return 0.5f;
        }
        txt = preProcess(txt);
        float num = 0;
        int decimalDigits = 0;
        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            if (c == TEN_CN) {
                if (i == 0) {
                    num = txt.length() > 1 ? 1 : 10;
                }
                continue;
            } else if (c == DECIMAL_DOT) {
                decimalDigits++;
                continue;
            }
            if (decimalDigits > 0) {
                num = (float) (num + Math.pow(0.1, decimalDigits++) * numMap.getOrDefault(c, c - '0'));
            } else {
                num = num * 10 + numMap.getOrDefault(c, c - '0');
            }
        }
        return num;
    }

    /**
     * 完整句子转换
     */
    public static String convert(String txt) {
        try {
            StringBuffer result = new StringBuffer();
            Matcher matcher = PATTERN.matcher(txt);
            while (matcher.find()) {
                float hour = toNum(matcher.group("HOUR"));
                float minute = toNum(matcher.group("MINUTE"));
                boolean hasHalf = matcher.group("HALF") != null;
                float totalMinutes = hasHalf ? hour * 60 + 30 + minute : hour * 60 + minute;
                matcher.appendReplacement(result, totalMinutes + "分钟");
            }
            matcher.appendTail(result);
            return result.toString();
        } catch (Exception e) {
            log.error("转换时间表述为分钟数字失败", e);
        }
        return txt;
    }
}
