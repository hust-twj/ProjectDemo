package com.hust_twj.zademo.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils {

    private static String[] punctuation = {",", "，", ".", "。", "?", "？", "!", "！", "、", "—", "_", "＿", "《", "》", "＜", "＞", ":", "：", "∶", ";", "；",
            "\"", "“", "”", "'", "‘", "’", "(", ")", "（", "）", "\\[", "\\]", "【", "】", "+", "＋", "[-]", "－", "–", "=", "＝",
            "&", "＆", "…", "⋯", "~", "～", "#", "＃", ">", "<", "%", "％", "*", "＊", "/", "／", "\\\\", "＼", "·", "@", "＠", " ", "　", "［", "］", "\r", "\n"};

    /**
     * 判断字符是否为null或空串，会去除两边空格
     *
     * @param src 待判断的字符
     * @return
     */
    public static boolean isEmpty(String src) {
        return src == null || TextUtils.isEmpty(src.trim());
    }

    /**
     * 是否符合邮箱格式
     *
     * @param src 源字符串
     * @return 是否符合邮箱格式
     */
    public static boolean isEmail(String src) {
        Pattern pattern = Pattern.compile("\\w+@(\\w+\\.){1,3}\\w+");
        return pattern.matcher(src).find();
    }

    /**
     * 是否包含特殊字符（除字母、数字外的字符为特殊字符）
     *
     * @param str 源字符串
     * @return 是否包含特殊字符
     */
    public static boolean hasSpecialLetter(String str) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher mat = pattern.matcher(str);
        return !mat.find();
    }

    /**
     * 是否手机号码
     * <p>只判断11位数字
     *
     * @param src 源字符串
     * @return 是否是手机号码
     */
    public static boolean isPhoneNumber(String src) {
        if (TextUtils.isEmpty(src)) {
            return false;
        }
        Pattern p = Pattern.compile("^\\d{11}$");
        Matcher m = p.matcher(src);
        return m.matches();
    }

    /**
     * 是否是密码
     *
     * @param src 源字符串
     * @return 是否是密码
     */
    public static boolean isPassword(String src) {
        Pattern p = Pattern.compile("^[A-Za-z0-9]*$");
        Matcher m = p.matcher(src);
        return m.matches();
    }

    /**
     * 是否是昵称和手机号
     *
     * @param nickNameID
     * @return
     */
    public static boolean isNumberFormat(String nickNameID) {
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(nickNameID);
        return m.matches();
    }

    /**
     * 中英文数字正则表达式(4-16位)
     *
     * @param str
     * @return
     */
    public static boolean isChineseEnglishFormat(String str) {
        Pattern p = Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9]+$");
        Matcher m = p.matcher(str);
        str = str.replaceAll("[^\\x00-\\xff]", "**");    //匹配双字节字符
        int length = str.length();
        return m.matches() && length >= 4 && length <= 16;
    }

    public static boolean isChinese(String str) {
        Pattern p = Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");
        Matcher m = p.matcher(str);
        // 判断是否为中文字符
        return m.matches();
    }

    /**
     * 只包含英文/数字和_
     *
     * @param str
     * @return
     */
    public static boolean isEnglishNumberAndUnderline(String str) {
        Pattern p = Pattern.compile("^[0-9,a-z,A-Z,_]{1,20}");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 只包含英文/数字
     *
     * @param str
     * @return
     */
    public static boolean isEnglishAndNumber(String str) {
        Pattern p = Pattern.compile("^[0-9,a-z,A-Z]{1,20}");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 只包含汉字/英文/数字和_
     *
     * @param str
     * @return
     */
    public static boolean isChineseEnglishNumberAndUnderline(String str) {
        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5,0-9,a-z,A-Z,_]{1,20}");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 截取一段字符的长度(汉、日、韩文字符长度为2),不区分中英文,如果数字不正好，则少取一个字符位
     *
     * @param str                原始字符串
     * @param specialCharsLength 截取长度(汉、日、韩文字符长度为2)
     * @return
     */
    public static String trim(String str, int specialCharsLength) {
        if (str == null || "".equals(str) || specialCharsLength < 1) {
            return "";
        }
        char[] chars = str.toCharArray();
        int charsLength = getCharsLength(chars, specialCharsLength);
        return new String(chars, 0, charsLength);
    }

    /**
     * 获取一段字符的长度，输入长度中汉、日、韩文字符长度为2，输出长度中所有字符均长度为1
     *
     * @param chars              一段字符
     * @param specialCharsLength 输入长度，汉、日、韩文字符长度为2
     * @return 输出长度，所有字符均长度为1
     */
    private static int getCharsLength(char[] chars, int specialCharsLength) {
        int count = 0;
        int normalCharsLength = 0;
        for (int i = 0; i < chars.length; i++) {
            int specialCharLength = getSpecialCharLength(chars[i]);
            if (count <= specialCharsLength - specialCharLength) {
                count += specialCharLength;
                normalCharsLength++;
            } else {
                break;
            }
        }
        return normalCharsLength;
    }


    /**
     * 获取一段字符的长度，输入长度中汉、日、韩文字符长度为2，输出长度中所有字符均长度为1
     *
     * @param str 一段字符
     * @return 输出长度，所有字符均长度为1
     */
    public static int getStringLength(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int normalCharsLength = 0;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            normalCharsLength += getSpecialCharLength(chars[i]);
        }
        return normalCharsLength;
    }

    /**
     * 字符串截取，输入长度中汉、日、韩文字符长度为2，输出长度中所有字符均长度为1，超过长度尾部加上<b>...</b>
     *
     * @param str         一段字符
     * @param limitLength 长度限制，超出截取尾部加上<b>...</b>
     * @return 输出长度，所有字符均长度为1
     */
    public static String subLength(String str, int limitLength) {
        final int length = getStringLength(str);
        String content = str;
        if (length > limitLength) {
            content = StringUtils.trim(str, limitLength) + "...";
        }
        return content;
    }

    /**
     * 获取字符长度：汉、日、韩文字符长度为2，ASCII码等字符长度为1
     *
     * @param c 字符
     * @return 字符长度
     */
    private static int getSpecialCharLength(char c) {
        if (isAscill(c)) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
     *
     * @param c, 需要判断的字符
     * @return boolean, 返回true,Ascill字符
     */
    private static boolean isAscill(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 为字符串添加双引号
     *
     * @param obj 字符串
     * @return 加入双引号后的完整字符串
     */
    public static String addQuotationMarks(String obj) {
        return "\"" + obj + "\"";
    }

    /**
     * 保留1位小数
     *
     * @param num
     * @return
     */
    public static String onlyOneFloat(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(num);
    }

    /**
     * 连接多个字符串标签
     *
     * @param tags                  所有字符标签
     * @param linker                标签之间的连接字符
     * @param isLastTagAppendLinker 最后一个标签末尾是否也需要追加linker
     * @return 连接得到的字符串
     */
    public static String concatTags(List<String> tags, String linker, boolean
            isLastTagAppendLinker) {
        if (tags != null) {
            int size = tags.size();
            if (size > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    sb.append(tags.get(i));
                    if (i != size - 1) {
                        sb.append(linker);
                    } else if (isLastTagAppendLinker) {
                        sb.append(linker);
                    }
                }
                return sb.toString();
            } else {
                return "";
            }
        }
        return null;
    }

    /**
     * 保留几位小数
     *
     * @param target         目标浮点数
     * @param fractionDigits 小数位数
     * @return 格式化后的字符串
     */
    public static String formatDouble(double target, int fractionDigits) {
        String format = "%." + fractionDigits + "f";
        return String.format(format, target);
    }

    /**
     * 只包含英文
     *
     * @param str
     * @return
     */
    public static boolean isJustEnglish(String str) {
        Pattern p = Pattern.compile("^[a-zA-Z]*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 只包含数字
     *
     * @param str
     * @return
     */
    public static boolean isJustNumber(String str) {
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String get334Format(String phone) {
        StringBuilder stringBuilder = new StringBuilder(phone);
        if (phone != null && phone.length() == 11) {
            stringBuilder.insert(7, " ").insert(3, " ");
        }
        return stringBuilder.toString();
    }

    /**
     * 将指定的字符串进行Base64编码.
     *
     * @param src 待编码的字符串
     * @return
     */
    public static String base64Encode(String src) {
        return Base64.encodeToString(src.getBytes(), Base64.DEFAULT);
    }

    /**
     * 将指定的字符串进行Base64解码.
     *
     * @param src 待解码的字符串
     * @return
     */
    public static String base64Decode(String src) {
        return new String(Base64.decode(src, Base64.DEFAULT));
    }

    /**
     * 颜色是否合法
     *
     * @param colorString
     * @return
     */
    public static boolean isColorString(String colorString) {
        Pattern p = Pattern.compile("^#([0-9a-fA-F]{6}|[0-9a-fA-F]{8})$");
        Matcher m = p.matcher(colorString);
        return m.matches();
    }

    /**
     * 只包含汉字/英文/数字和_
     * 不限长度
     *
     * @param str
     * @return
     */
    public static boolean isChineseEnglishNumberAndUnderlineNoLimitedLength(String str) {
        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5,0-9,a-z,A-Z,_]*");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 只包含汉字/英文/数字和正常的符号
     * 不限长度
     *
     * @param str
     * @return
     */
    public static boolean isChineseEnglishNumberAndPunctuationNoLimitedLength(String str) {
        StringBuilder regex = new StringBuilder();
        regex.append("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D,0-9,a-z,A-Z");
        for (int i = 0; i < punctuation.length; i++) {
            regex.append(",");
            regex.append(punctuation[i]);
        }
        regex.append("]*");
        Pattern p = Pattern.compile(regex.toString());
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String getNextCloseWindow(int time) {
        int hour = time / (60 * 60);
        int minute = time / 60;
        int second = time % 60;
        if (hour > 0) {
            minute = (time % (60 * 60)) / 60;
            if (minute > 0 && second > 0) {
                return "距离下一次密聊还有:" + hour + "小时" + minute + "分" + second + "秒";
            } else if (minute > 0) {
                return "距离下一次密聊还有:" + hour + "小时" + minute + "分";
            } else if (second > 0) {
                return "距离下一次密聊还有:" + hour + "小时" + second + "秒";
            } else {
                return "距离下一次密聊还有:" + hour + "小时";
            }
        } else if (minute > 0) {
            if (second > 0) {
                return "距离下一次密聊还有:" + minute + "分" + second + "秒";
            } else {
                return "距离下一次密聊还有:" + minute + "分";
            }
        } else {
            return "距离下一次密聊还有:" + second + "秒";
        }
    }

    public static void main(String[] arg) {
        String s = "哈哈哈哈\n哈哈哈哈哈哈\n\n哈哈哈哈哈\n\n\n哈哈fr453哈哈\n\n\n\n\n哈哈哈a_ffffffffffffffehi发鞥结果" +
                ",，！!、—?？.。··《》<>:：；;\"\"“”’‘/'/' －-– \\\\ 哈哈~[]+(),.?!、_——《》;;:+-=&……~#<>%*@∶＿";
        String testStr = "你好，测试。";

        System.out.println("" + reduceCenterLineBreak(s));
    }

    /**
     * 精简中间连续空行为一个
     *
     * @param str
     * @return
     */
    public static String reduceCenterLineBreak(String str) {
        Pattern p = Pattern.compile("\n{3,}");
        Matcher m = p.matcher(str);
        return m.replaceAll("\n\n");
    }

    public static String toUpperCase(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        } else {
            return text.toUpperCase();
        }
    }

    public static String toLowerCase(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        } else {
            return text.toLowerCase();
        }
    }


}
