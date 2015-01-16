package trash.test;

import simhash.Fingerprint;
import simhash.HammingDistance;
import trash.util.ChangeCode;
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
public class Test1 {

    public static void main(String[] args) {



//        //测试语句1 无权值
//        String str1 = "泰国jetanin醫院做試管嬰兒多少费用 泰国jetanin醫院做試管嬰兒多少费用【爱心国际医疗】 " +
//                "400-878-5590 QQ250-9157-860 王顾问 泰国試管嬰兒技术现已经闻于世，在技术上堪与美国相比，从某种意义上说，" +
//                "性价比更高于美国：美国試管嬰兒的成功率在50%~65%，而泰国的是 50%~60%，差距仅有一点点，但在费用上，泰国做 試管嬰兒比美国便宜得多。" ;
//        //语句清洗，繁体转简体
//        str1 = ChangeCode.toSimple(str1);
//        str1 = MyStringUtil.formatPunctuationToAString(str1);
//        //分词
//        str1 = SplitSentence.splitWordBySpace(str1);
//        //去单个汉字
//        str1 = MyStringUtil.toDeleteChineseCharacter(str1);
//        System.out.println(str1);
//
//
//        //测试语句2 无权值
//        String str2 = "去泰国做試管嬰兒费用大概多少呢?全部费用要预算在15万人民币左右，包括医疗费用，住宿费，翻译费等，" +
//                "还可以选择胎儿性别。泰国第叁代試管嬰兒，技术已 经是非常成熟了，由于泰国国家的政策支持， " +
//                "因此可以根据自己的需求胎儿的选择性别，因此泰国第叁代試管嬰兒技术得以经常性的运用，皆可熟能生巧了。" +
//                "第叁代試管嬰兒也称胚胎植入前遗传学诊断 (PGD)，指在IVF-ET的胚胎移植前，取胚胎的 遗传物质进行分析，" +
//                "诊断是否有异常，筛选健康胚胎移植，防止遗传病传递的方法。最新的第叁代試管嬰兒技术能排除基因缺陷，对付遗传疾病。";
//        //语句清洗，繁体转简体
//        str2 = ChangeCode.toSimple(str2);
//        str2 = MyStringUtil.formatPunctuationToAString(str2);
//        //分词
//        str2 = SplitSentence.splitWordBySpace(str2);
//        //去单个汉字
//        str2 = MyStringUtil.toDeleteChineseCharacter(str2);
//        System.out.println(str2);
//
//        //语句1的指纹值
//        String fingerprint1 = Fingerprint.getFingerprint(str1) ;
//        //语句2的指纹值
//        String fingerprint2 = Fingerprint.getFingerprint(str2) ;
//        //海明距离
//        int hammingInt = HammingDistance.getHammingByBitString(fingerprint1, fingerprint2) ;
//        //打印海明距离，越小，说明越相似。
//        System.out.println(hammingInt);




        //测试语句1 无权值
//        String str1 = "相超【迷】敏博那凡【迷】懿宁博赡衍妘乐语\n" +
//                "        昱兹【药】苗苯艺斐【奸】语哲华学苗苯\n" +
//                "        QQ:⑥i⑨o⑤s②g⑧u⑧d0t⑨n0" ;
        String str1 = "我司大量招聘 实习生 寒假工 发单价１３．５元 " +
                "欢迎有资源的同行老板，工头 学生代表 老师校长 等……到我司洽谈合作 工厂可以选择东莞 深圳 惠州 广州欢迎有资源的朋友速度" +
                "联系电话13189722938 殷生 有资源的朋友加群聊互赢互利群号73395311.查看详情另招临时工２００名发单１１．５元要求２６岁以下长白班坐班手机组装厂深圳 ";
        //语句清洗，繁体转简体
        str1 = ChangeCode.toSimple(str1);
        //str1 = MyStringUtil.formatPunctuationToAString(str1);
        str1 = MyStringUtil.deleteDigit(str1);
        //分词
        str1 = SplitSentence.splitWordBySpace(str1);
        System.out.println(str1);
        //去单个汉字
        str1 = MyStringUtil.deleteChineseCharacter(str1);
        System.out.println(str1);


        //测试语句2 无权值
        String str2 = "1、20倍杠杆，5%保证金2、资金银行第三方，安全3、非农无限制,出入金及时4、省批文，国务院备案，金融办监管。5、返佣及时，日返佣，不拖欠6、品种：1KG/15KG/30KG7、想做终端、代理可以给返佣8、个代低门槛、资金保证、专业指导\n" +
                "有意者详聊 ！！ 陈经理：13424265630 QQ:1274314391\n" +
                "返佣方式式：纯手续费、手续费+红利、打包 80%返佣起步\n" +
                "多个选择，绝对不会后悔，相信我们认识绝对是开心的开始。";
        //语句清洗，繁体转简体
        str2 = ChangeCode.toSimple(str2);
        //str2 = MyStringUtil.formatPunctuationToAString(str2);
        str2 = MyStringUtil.deleteDigit(str2);
        //分词
        str2 = SplitSentence.splitWordBySpace(str2);
        //去单个汉字
        str2 = MyStringUtil.deleteChineseCharacter(str2);
        System.out.println(str2);

        //语句1的指纹值
        String fingerprint1 = Fingerprint.getFingerprint(str1) ;
        //语句2的指纹值
        String fingerprint2 = Fingerprint.getFingerprint(str2) ;
        //海明距离
        int hammingInt = HammingDistance.getHammingByBitString(fingerprint1, fingerprint2) ;
        //打印海明距离，越小，说明越相似。
        System.out.println(hammingInt);
    }
}
