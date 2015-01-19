package com.lhx.simplejsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lhx on 15-1-16 下午3:59
 *
 * @project gitjava
 * @package com.lhx.simplejsoup
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class JsoupSample {

    public static void parseDocFromString(){
        String html = "<html><head><title>Parse a document from a String</title></head><body><p>Parsed HTML into a doc.</p></body></html>" ;
        //从字符串中解析
        Document document = Jsoup.parse(html) ;
        System.out.println(document.title());

    }

    public static void loadDocByUrl(String urlPath) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("searcharg", "java");
        params.put("searchtype", "t");
        params.put("SORT", "DZ");
        params.put("extended", "0");
        Document document = Jsoup.connect(urlPath).userAgent("Mozilla").timeout(10*1000).data(params).get();
        System.out.println(document.html());
    }

    public static void loadDocFromFile() throws IOException{
        File inputFile = new File("input.html");
        Document doc = Jsoup.parse(inputFile, "UTF-8");
        System.out.println(doc.html());  //打印获取的html源码
    }

    //995911422@qq.com Judyzjb52088
    public static void main(String[] args) throws IOException {
        //parseDocFromString();
        loadDocByUrl("http://liuzhichao.com/p/1490.html");
    }


}
