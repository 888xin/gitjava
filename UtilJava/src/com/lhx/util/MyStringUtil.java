package com.lhx.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lhx on 14-12-18 下午2:38
 * 过滤字符串
 * @project javaProject
 * @package com.lifeix.trash.util
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
public class MyStringUtil {
    /**
     * 过滤掉一切标点符号，多个空格合并成一个
     * @param s
     * @return
     */
    public static String formatPunctuation(String s){
        String str = s.replaceAll("\\pP|\\pS|\\n"," ");
        return str.replaceAll("\\s{1,}"," ") ;
    }

    /**
     * 过滤一切标点符号和空格，返回一条没有间隔的字符串
     * @param s 待处理的字符串
     * @return 没有间隔的字符串
     */
    public static String formatPunctuationToAString(String s){
        String str = s.replaceAll("\\pP|\\pS|\\n"," ");
        return str.replaceAll("\\s{1,}","") ;
    }

    /**
     * 过滤多个空格，多个空格变成一个
     * @param s
     * @return
     */
    public static String formatSpace(String s,String c){
        return s.replaceAll("\\s{1,}",c) ;
    }

    /**
     * 统计一个字符串里面有多少个空格
     * @param s
     * @return
     */
    public static int findSpaceNumberInString(String s){
        String str = s.replaceAll("\\s{1,}","") ;
        return s.length() - str.length() ;
    }

    public static int getWordsCount(String str){
        String[] strs = str.split(" ");
        return strs.length ;
    }

    /**
     * 统计语句中某个词出现的次数
     * @param s 匹配的词
     * @param str 待过滤的语句
     * @return
     */
    public static int findStopWordNumberInString(String s, String str){
        Pattern p = Pattern.compile(s,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        int count = 0;
        while(m.find()){
            count ++;
        }
        return count ;
    }

    /**
     * 对字符串过滤掉单个字符
     * @param str
     * @return
     */
    public static String deleteChineseCharacter(String str){
        StringBuilder stringBuilder = new StringBuilder(str);
        int length = stringBuilder.length();
        int m = 0 ;
        while (m < length){
            //首先对首个汉字进行判断
            if (m == 0){
                if (stringBuilder.charAt(m)!=' ' && stringBuilder.charAt(m+1)==' '){
                    stringBuilder.deleteCharAt(m);
                    length -- ;
                    stringBuilder.deleteCharAt(m);
                    length -- ;
                    continue;
                }
                m ++ ;
            } else {
                //空字符串跳过
                if (stringBuilder.charAt(m)==' '){
                    m ++ ;
                } else if (stringBuilder.charAt(m-1)==' ' && stringBuilder.charAt(m)!=' ' && m+1 != length && stringBuilder.charAt(m+1)==' ' ){
                    stringBuilder.deleteCharAt(m);
                    length -- ;
                    stringBuilder.deleteCharAt(m);
                    length -- ;
                } else if (stringBuilder.charAt(m-1)==' ' && stringBuilder.charAt(m)!=' ' && m+1 == length){
                    stringBuilder.deleteCharAt(m);
                    break ;
                } else if (stringBuilder.charAt(m-1)!=' ' && stringBuilder.charAt(m)!=' ' && m+1 != length && stringBuilder.charAt(m+1)!=' '){
                    m += 3 ;
                } else {
                    m ++ ;
                }
            }
        }
        return stringBuilder.toString() ;
    }

    /**
     * 删除字符串中的数字
     * @param str
     * @return
     */
    public static String deleteDigit(String str){
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(str);
        String result = matcher.replaceAll("");
        return result ;
    }

//    public static void main(String[] args) {
//        String str = "我 是 中国人 你 不要 欺负 我 喔" ;
//        String str = "  是 s 34 中国人 你 不要 我 四面楚歌声范德萨范德萨" ;
//        System.out.println( toDeleteChineseCharacter(str) );
//    }

    public static String formatChinese(String str){
        String str1 = str.replaceAll("[^\\u4E00-\\u9FA5]+"," ");
        return str1 ;
//        Pattern pattern = Pattern.compile("^[\\u4E00-\\u9FA5]");
//        Matcher matcher = pattern.matcher(str);
//        String result = matcher.replaceAll("");
//        return result ;
    }

    public static void main(String[] args) {
        String str = "是23士大夫2343电话2的23的456我" ;
        System.out.println( deleteDigit(str) );
    }
}
