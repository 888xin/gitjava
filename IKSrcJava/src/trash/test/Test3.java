package trash.test;

import trash.util.MyStringUtil;
import trash.util.SplitSentence;

/**
* Created by lhx on 14-12-18 上午9:05
*
* @project javaProject
* @package com.lifeix.trash.test
* @blog http://blog.csdn.net/u011439289
* @email 888xin@sina.com
* @Description
*/
public class Test3 {

    public static void main(String[] args) {
        String str1 = "";
        str1 = MyStringUtil.formatPunctuationToAString(str1) ;
        str1 = SplitSentence.splitWordBySpace(str1) ;
        //去单个汉字
        str1 = MyStringUtil.deleteChineseCharacter(str1);
        System.out.println(str1);
    }
}
