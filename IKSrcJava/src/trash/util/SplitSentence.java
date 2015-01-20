package trash.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhx on 14-12-18 上午9:03
 * 应用IKAnalyzer分词工具进行分词
 * @project javaProject
 * @package com.lifeix.trash.util
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @Description
 */
public class SplitSentence {
    /**
     * 传入字符串，应用IKAnalyzer分词工具进行分词
     * @param str
     * @return
     */
    public static String splitWordBySpace(String str){
        StringBuilder buffer = new StringBuilder();
        //创建分词对象
        Analyzer anal = new IKAnalyzer(false);
        StringReader reader = new StringReader(str);
        //分词
        TokenStream ts = anal.tokenStream("",reader);
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
        //遍历分词数据
        try {
            while (ts.incrementToken()){
                buffer.append(term.toString() + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
        return buffer.toString();
    }

    /**
     * 传入字符串，应用IKAnalyzer分词工具进行分词,切出来的词语放到集合里面
     * @param str
     * @return
     */
    public static List<String> splitWordInList(String str){
        List<String> words = new ArrayList<String>();
        //创建分词对象
        Analyzer anal = new IKAnalyzer(true);
        StringReader reader = new StringReader(str);
        //分词
        TokenStream ts = anal.tokenStream("",reader);
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
        //遍历分词数据
        try {
            while (ts.incrementToken()){
                words.add(term.toString()) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
        return words;
    }


    public static String splitWordForStr(String str){
        StringBuilder buffer = new StringBuilder();
        //创建分词对象
        Analyzer anal = new IKAnalyzer(true);
        StringReader reader = new StringReader(str);
        //分词
        TokenStream ts = anal.tokenStream("",reader);
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
        //遍历分词数据
        try {
            while (ts.incrementToken()){
                buffer.append(term.toString() + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(splitWordBySpace("情凭谁来定错对 我始终不想去追寂寞路上遇痴心相恋也有过痛苦一堆如能从头遇见你我始终不会后退将伤心收于记忆中仍没法去剪碎浓情蜜意尽过去 冻好比一杯冷水独自默默望苍天 心底里满载往昔唏嘘迷雾已渐渐散退过去的经已逝去前路漫漫显得崎岖 还是要我去面对回忆起当天的欢笑是光阴冲洗不去在这一生中这一生中沉醉寂寞憔悴这世界可有谁逝去了的爱情陪孤单寄居寂寞憔悴人痛心因你别去我已不懂得哭笑望着你身影远去难得当天的相爱是你我都倾出所有令这一生中这一生中无悔但寂寞憔悴这世界可有谁逝去了的爱情陪孤单寄居寂寞憔悴人痛心因你别去我已不懂得哭笑就让我消失告退我已不懂得哭笑就让我消失告退我已不懂得哭笑就让我风中告退"));
    }
}
