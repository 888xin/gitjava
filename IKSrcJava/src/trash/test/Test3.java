package trash.test;

import simhash.Fingerprint;
import simhash.HammingDistance;
import trash.util.JdbcUtils2;
import trash.util.MyStringUtil;
import trash.util.ReadFile;
import trash.util.SplitSentence;

import java.io.File;
import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
//        String str1 = "情凭谁来定错对 我始终不想去追寂寞路上遇痴心相恋也有过痛苦一堆如能从头遇见你我始终不会后退将伤心收于记忆中仍没法去剪碎浓情蜜意尽过去 冻好比一杯冷水独自默默望苍天 心底里满载往昔唏嘘迷雾已渐渐散退过去的经已逝去前路漫漫显得崎岖 还是要我去面对回忆起当天的欢笑是光阴冲洗不去在这一生中这一生中沉醉寂寞憔悴这世界可有谁逝去了的爱情陪孤单寄居寂寞憔悴人痛心因你别去我已不懂得哭笑望着你身影远去难得当天的相爱是你我都倾出所有令这一生中这一生中无悔但寂寞憔悴这世界可有谁逝去了的爱情陪孤单寄居寂寞憔悴人痛心因你别去我已不懂得哭笑就让我消失告退我已不懂得哭笑就让我消失告退我已不懂得哭笑就让我风中告退";
//        str1 = MyStringUtil.formatPunctuationToAString(str1) ;
//        str1 = SplitSentence.splitWordBySpace(str1) ;
//        //去单个汉字
//        str1 = MyStringUtil.deleteChineseCharacter(str1);
//        System.out.println(str1);


//        Connection connection = JdbcUtils2.getConnection("jdbc:mysql://localhost:3306/song?characterEncoding=UTF8",
//                "root", "465864") ;
//        List<Map<String, Object>> list = JdbcUtils2.getData(connection, "select * from lyrics ");
//        for (int i = 0; i < list.size(); i++) {
//            Map<String, Object> map = list.get(i);
//            int lid = (int) map.get("lid");
//            String lrc = (String) map.get("lrc");
//            lrc = MyStringUtil.formatPunctuationToAString(lrc) ;
//            lrc = SplitSentence.splitWordBySpace(lrc) ;
//            //去单个汉字
//            lrc = MyStringUtil.deleteChineseCharacter(lrc).trim();
//            String fingerprint = Fingerprint.getFingerprint(lrc) ;
//            JdbcUtils2.update(connection,"update lyrics set keywords=?, hashcode=? where lid=? ",lrc, fingerprint,lid+"");
//        }
//        //用完数据库，释放连接
//        JdbcUtils2.free(null,null,connection);


        Connection connection = JdbcUtils2.getConnection("jdbc:mysql://localhost:3306/song?characterEncoding=UTF8",
                "root", "465864") ;
        List<Map<String, Object>> list = JdbcUtils2.getData(connection, "select * from lyrics ");
        Set<String> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String hashcode = (String) map.get("hashcode");
            set.add(hashcode);
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            int lid = (int) map.get("lid");
            String hashcode = (String) map.get("hashcode");
            for (String s : set) {
                //海明距离
                int hammingInt = HammingDistance.getHammingByBitString(hashcode, s) ;
                if (hammingInt < 20){
                    System.out.print(lid + " : ");
                    System.out.print(s + "  ");
                }
            }
            System.out.println("===============");
        }
        //用完数据库，释放连接
        JdbcUtils2.free(null,null,connection);

    }
}
