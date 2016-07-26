package com.example.eshenghuo.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目中使用的字符工具
 * Created by My on 2016/3/11.
 */
public class StrUtils {
    private static boolean isCorrectInput;
    /**
     * 验证手机格式
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    public static boolean isMobileFormat(String mobiles) {
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String mobileRegex = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(mobileRegex);
    }
    /**
     * 验证输入的是否是汉字
     */
    public static boolean isChineseFormat(String input) {
        isCorrectInput = Pattern.compile("[a-zA-Z]").matcher(input).matches();
        return isCorrectInput;
    }
    /**
     * 验证输入的是否是字母
     */
    public static boolean isEnglishFormat(String input) {
        isCorrectInput = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(input).matches();
        return isCorrectInput;
    }

    /**
     * 排除非中文英文和数字的字符
     * @param input
     * @return
     */
    public static boolean isValidWord(String input){
        String pattern = "[\u4e00-\u9fa5\\w]+";
        return Pattern.compile(pattern).matcher(input).matches();
    }

    /**
     * 判断邮箱
     * @param email
     * @return
     */
    public  static boolean isEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.find();
    }

}
