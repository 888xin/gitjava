package trash.test;

import trash.util.JudgePhoneNumber;
import trash.util.MyStringUtil;
import trash.util.SplitSentence;

/**
 * Created by lhx on 15-1-4 上午10:18
 *
 * @project javaProject
 * @package trash.test
 * @Description 分析用户行为，两个QQ号码或两个电话号码或一个QQ号码一个电话号码以上的，重点关注。一个电话号码或一个QQ号码需要关注
 * 重点关注QQ，手机，电话，号码，加Q等字样
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class Test2 {
    public static void main(String[] args) {
        String str1 = "我司大量招聘 实习生　寒假工　发单价１３．５元 　欢迎有资源的同行老板，工头 学生代表 老师校长 等……到我司洽谈合作 工厂可以选择东莞 深圳 惠州 广州欢迎有资源的朋友速度联系电话13189722938 殷生 有资源的朋友加群聊互赢互利群号73395311" ;
        String str2 = "浏 阳 小 姐 信 息 :１５１－２２１７－５３０２ 菲 菲 浏 阳 叫 小 姐 请 致 电 :１５１－２２１７－５３０２ 菲 菲 【 提 前 ♀ １５１－２２１７－５３０２ ♀ 预 约 】 浏 阳 酒 店 小 姐 １５１－２２１７－５３０２ 菲 菲 『 浏 阳 小 姐 』『 浏 阳 哪 里 小 姐 漂 亮 』 『 浏 阳 什 么 地 方 有 小 姐 』 １５１－２２１７－５３０２『 浏 阳 桑 拿 小 姐 』 『 浏 阳 洗 浴 中 心 小 姐 』 『 浏 阳 休 闲 会 所 小 姐 』 １５１－２２１７－５３０２ 『 浏 阳 红 灯 区 小 姐 』 『 浏 阳 小 姐 一 夜 情 服 务 』『 浏 阳 小 姐 援 交 』１５１－２２１７－５３０２ 『 浏 阳 哪 条 路 有 小 姐 』『浏 阳 酒 店 宾 馆 小 姐 』 『 浏 阳 三 陪 小 姐 』１５１－２２１７－５３０２ 『 浏 阳 桑 拿 洗 浴 小 姐 』 『 浏 阳 哪 里 的 小 姐 漂 亮 』 『 浏 阳 小 姐 价 格 』 １５１－２２１７－５３０２ 『 浏 阳 哪 条 街 有 小 姐 』 『 浏 阳 哪 里 的 小 姐 多 』 『 浏 阳 小 姐 电 话 』 １５１－２２１７－５３０２ 『 浏 阳 哪 个 宾 馆 有 小 姐 』 『 浏 阳 哪 个 酒 店 有 小 姐 』 【 卐 】 服 务 电 话 :１５１－２２１７－５３０２ -- 菲 菲 小 姐 -- 【 ¤ 】" ;
        String str3 = "泰国jetanin醫院做試管嬰兒多少费用 泰国jetanin醫院做試管嬰兒多少费用【爱心国际医疗】 400-878-5590 QQ250-9157-860 王顾问 泰国試管嬰兒技术现已经闻于世，在技术上堪与美国相比，从某种意义上说，性价比更高于美国：美国試管嬰兒的成功率在50%~65%，而泰国的是 50%~60%，差距仅有一点点，但在费用上，泰国做 試管嬰兒比美国便宜得多。" ;
        String str4 = "去泰国做試管嬰兒费用大概多少呢?全部费用要预算在15万人民币左右，包括医疗费用，住宿费，翻译费等，还可以选择胎儿性别。泰国第叁代試管嬰兒，技术已 经是非常成熟了，由于泰国国家的政策支持， 因此可以根据自己的需求胎儿的选择性别，因此泰国第叁代試管嬰兒技术得以经常性的运用，皆可熟能生巧了。第叁代試管嬰兒也称胚胎植入前遗传学诊断 (PGD)，指在IVF-ET的胚胎移植前，取胚胎的 遗传物质进行分析，诊断是否有异常，筛选健康胚胎移植，防止遗传病传递的方法。最新的第叁代試管嬰兒技术能排除基因缺陷，对付遗传疾病。" ;
        String str5 = "在家淘宝兼职工作啦！YY语音工会招全职客服，兼职操作员，接待员，培训员等职位，工资日结30-80不等,还可以免费代理热销面膜和各种高仿暴利名包工会频道日常在线会员5000 赚钱学习两不误，想月入过2000的亲们不用走弯路了！请加QQ 19184784 空间日志有介绍哦" ;
        String str6 = "1、20倍杠杆，5%保证金2、资金银行第三方，安全3、非农无限制,出入金及时4、省批文，国务院备案，金融办监管。5、返佣及时，日返佣，不拖欠6、品种：1KG/15KG/30KG7、想做终端、代理可以给返佣8、个代低门槛、资金保证、专业指导\n" +
                "有意者详聊 ！！ 陈经理：13424265630 QQ:1274314391\n" +
                "返佣方式式：纯手续费、手续费+红利、打包 80%返佣起步\n" +
                "多个选择，绝对不会后悔，相信我们认识绝对是开心的开始。" ;
        String str7 = "广西银河大宗商品交易市场：\n" +
                "一 ：广西省省批文，自治区批文。\n" +
                "国内首个双批文平台\n" +
                "二：四种仓单 5kg 15kg 50kg 100kg\n" +
                "三：最大50倍杠杆 2%预付款\n" +
                "四：手续费单边万分之六 5个点点差\n" +
                "五：资金建行托管，安全可靠\n" +
                "六 免费指导 群指导 飞信指导 短信指导\n" +
                "为回馈新老客户，高额返佣70% 周返 相当于6.5个点出成本\n" +
                "电话：15124509051 18346189062\n" +
                "qq：1439103425" ;
        String str8 = "投资有道+qq群 159963688 验证：贴吧369,验证必填。\n" +
                "不论你做股票、现货（黄金，白银）、期货、外汇，等投资产品，了解投资趋势 +qq群159963688 验证:贴吧369\n" +
                "给广大投资者提供纯绿色投资交流的平台咨询." ;
        String str9 = "7本公司销售各种高档高仿香烟\n" +
                "美美本香烟口感好，质量优，价格便宜\n" +
                "加Q355123459电话：13699776367 掩嘴笑" ;
        String str10 = "7本公司销售各种高档高仿香烟\n" +
                "美美本香烟口感好，质量优，价格便宜\n" +
                "加Q355123459电话：13699776367 掩嘴笑" ;
        String str11 = "泰弘银火爆招商，0点差，8点回本，个人65%起，公司代理75%起，工行托管，每周二返佣，非农无限制挂单，止损止盈 双向（涨跌）买卖 挂单10点起挂，多元世纪软件，盘面稳定，安全，不卡盘，不滑点，多个品种，5到100千克，满足不同投资群体，有意者可私聊QQ:249111746 2300046112 官网：www.taihongpme.com" ;
        String str12 = "相超【迷】敏博那凡【迷】懿宁博赡衍妘乐语  \n" +
                "昱兹【药】苗苯艺斐【奸】语哲华学苗苯\n" +
                "QQ:⑥i⑨o⑤s②g⑧u⑧d0t⑨n0" ;

        //语句清洗
        str1 = MyStringUtil.formatPunctuationToAString(str1) ;
        str1 = SplitSentence.splitWordBySpace(str1) ;
        str2 = MyStringUtil.formatPunctuationToAString(str2) ;
        str2 = SplitSentence.splitWordBySpace(str2) ;
        str3 = MyStringUtil.formatPunctuationToAString(str3) ;
        str4 = MyStringUtil.formatPunctuationToAString(str4) ;
        str5 = MyStringUtil.formatPunctuationToAString(str5) ;
        str6 = MyStringUtil.formatPunctuationToAString(str6) ;
        str7 = MyStringUtil.formatPunctuationToAString(str7) ;


        System.out.println(str1);
        System.out.println(str2);

        //统计号码个数
        int n1 = JudgePhoneNumber.getNumbersNoFromStr(str1);
        int n2 = JudgePhoneNumber.getNumbersNoFromStr(str2);
        int n3 = JudgePhoneNumber.getNumbersNoFromStr(str3);
        int n4 = JudgePhoneNumber.getNumbersNoFromStr(str4);
        int n5 = JudgePhoneNumber.getNumbersNoFromStr(str5);
        int n6 = JudgePhoneNumber.getNumbersNoFromStr(str6);
        int n7 = JudgePhoneNumber.getNumbersNoFromStr(str7);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
        System.out.println(n5);
        System.out.println(n6);
        System.out.println(n7);

        //关键字统计
        //去掉单个字符
        str1 = MyStringUtil.deleteChineseCharacter(str1);
        //去掉数字
        str1 = MyStringUtil.deleteDigit(str1);
        System.out.println(str1.trim());
        System.out.println(MyStringUtil.getWordsCount(str1.trim()));

    }
}
