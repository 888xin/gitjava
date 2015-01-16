package com.lhx.util;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by lhx on 14-12-19 下午4:00
 *
 * @project javaProject
 * @package com.lifeix.trash.util
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
public class SentenceUtil {

    /**
     * 统计停用词和号码的比率
     * @param str
     * @return 双精度数组 第一个表示停用词的比率，第二个表示号码的比率
     */
    public static double[] getPhonePerAndStopwordPer(String str){
        //字符串清洗
        String clearStr = MyStringUtil.formatPunctuationToAString(str);
        System.out.println(clearStr);
        //分词
        List<String> words = SplitSentence.splitWordInList(clearStr);

        int stopNum = 0 ;
        int phoneNum = 0 ;
        int size = words.size();
        for (String word : words) {
            if (":::::=====".equals(word)){
                stopNum ++ ;
            }
            if (Pattern.compile("(?<!\\d)(?:(?:\\d{7,12}))(?!\\d)").matcher(word).find()){
                phoneNum ++ ;
            }
        }
        double[] returnDouble = new double[2] ;
        //计算停用词数量/所有词汇
        returnDouble[0] = (double)stopNum / size ;
        //计算号码数量/所有词汇
        returnDouble[1] = (double)phoneNum / size ;
        return returnDouble ;
    }
}
