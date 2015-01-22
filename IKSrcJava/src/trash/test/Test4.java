package trash.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;
import trash.util.SplitSentence;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by lhx on 15-1-22 上午10:38
 *
 * @project gitjava
 * @package trash.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class Test4 {
    public static void main(String[] args) throws IOException {
//        long starttime = System.currentTimeMillis();
//        String str = "舞女惊艳";
//        String str1 = "无修正aBcv";
//        String str2 = "吸精大神";
//        String str3 = "夏川纯结";
//        String str4 = "小xue纯结";
//        str = SplitSentence.splitWordBySpace(str) ;
//        str1 = SplitSentence.splitWordBySpace(str1) ;
//        str2 = SplitSentence.splitWordBySpace(str2) ;
//        str3 = SplitSentence.splitWordBySpace(str3) ;
//        str4 = SplitSentence.splitWordBySpace(str4) ;
//        System.out.println(str);
//        System.out.println(str1);
//        System.out.println(str2);
//        System.out.println(str3);
//        System.out.println(str4);
//        System.out.println(System.currentTimeMillis() - starttime);


        Analyzer analyzer = new IKAnalyzer(false);
        String str = "舞女惊艳";
        StringReader reader = new StringReader(str);
        long starttime = System.currentTimeMillis();
        TokenStream ts = analyzer.tokenStream("",reader);
        Token token = new Token();
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
        while (ts.incrementToken()){
            System.out.println(term);
        }
        reader.close();
        System.out.println(System.currentTimeMillis() - starttime);
    }
}
