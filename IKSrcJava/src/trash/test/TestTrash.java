package trash.test;

import trash.util.MyStringUtil;
import trash.util.SplitSentence;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by lhx on 14-12-19 下午3:00
 *
 * @project javaProject
 * @package com.lifeix.trash.test
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
public class TestTrash {
    public static void main(String[] args) {
        //插入字符串
        String str = "7本公司销售各种高档高仿香烟\n" +
                "美美本香烟口感好，质量优，价格便宜\n" +
                "加Q355123459电话：13699776367 掩嘴笑";
        //字符串清洗
        String clearStr = MyStringUtil.formatPunctuationToAString(str);
        System.out.println(clearStr);
        //分词
        List<String> words = SplitSentence.splitWordInList(clearStr);
        //计算停用词数量/所有词汇
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
        double sd = (double)stopNum / size ;
        System.out.println(sd);
        //计算号码数量/所有词汇
        double pd = (double)phoneNum / size ;
        System.out.println(pd);
    }
}
