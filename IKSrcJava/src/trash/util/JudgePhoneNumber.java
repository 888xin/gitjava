package trash.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lhx on 14-12-18 下午2:56
 * 验证手机号码和固定电话
 * @project javaProject
 * @package com.lifeix.trash.util
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
public class JudgePhoneNumber {

    /**
     * 判断字符串是否为手机号码
     * @param str
     * @return
     */
    public static boolean isMobile(String str){
        Pattern p = null ;
        Matcher m = null ;
        boolean b = false ;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
        m = p.matcher(str);
        b = m.matches();
        return b ;
    }

    /**
     * 判断字符串是否为固定号码
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null,p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if(str.length() >9)
        {   m = p1.matcher(str);
            b = m.matches();
        }else{
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 从字符串中提取出手机和固定电话号码
     * 3-4位区号，7-8位直播号码，1－4位分机号
     * 12345678901、1234-12345678-1234
     * @param str
     * @return
     */
    public static String getPhoneNumberFromStr(String str){
//        Pattern pattern = Pattern.compile("(?<!\\d)  (?: (?:1[3458]\\d{9})  |  (?:861[358]\\d{9}) |  (?:[0][1-9]{2,3}-[0-9]{5,10}) |  (?:0\\d{9,11}) | (?:[1-9]{1}[0-9]{5,8})  )(?!\\d)");
//        Pattern pattern =  Pattern.compile("(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)");
//        Pattern pattern =  Pattern.compile("(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9})|(?:0\\d{9,10}))(?!\\d)");
        Pattern pattern =  Pattern.compile("(?<!\\d)(?:(?:1[3458]\\d{9})|(?:861[358]\\d{9})|(?:(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|(?:0\\d{9,11}))(?!\\d)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer bf = new StringBuffer(64);
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }


    public static String getNumbersFromStr(String str){
        Pattern pattern =  Pattern.compile("(?<!\\d)(?:(?:\\d{7,12}))(?!\\d)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer bf = new StringBuffer(64);
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }


    /**
     * 获得字符串中号码的个数
     * @param str
     * @return
     */
    public static int getNumbersNoFromStr(String str){
        Pattern pattern =  Pattern.compile("(?<!\\d)(?:(?:\\d{7,12}))(?!\\d)");
        Matcher matcher = pattern.matcher(str);
        int num = 0 ;
        while (matcher.find()) {
            num ++ ;
        }
        return num;
    }

    public static boolean hasPhoneNumberInStr(String str){
        Pattern pattern =  Pattern.compile("(?<!\\d)(?:(?:1[3458]\\d{9})|(?:861[358]\\d{9})|(?:(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|(?:0\\d{9,11}))(?!\\d)");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static void main(String[] args) {
        String str = "浏阳小姐信息15122175302菲菲浏阳叫小姐请致电15122175302菲提前14623429072预约浏0779-7203345阳酒店07797203345小姐15122175302菲菲浏阳";
//        String str = "15122175302";
        System.out.println( str.contains("酒店") );
        System.out.println(hasPhoneNumberInStr(str));
    }
}
