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
}
