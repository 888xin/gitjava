package com.lhx.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhx on 15-1-16 上午10:06
 *
 * @project gitjava
 * @package com.lhx.solrj
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class AddField {
    public static void main(String[] args) {
        String url = "http://localhost:8080/solr";
        SolrServer server = new HttpSolrServer(url);
        SolrInputDocument doc1 = new SolrInputDocument();

        doc1.addField("id","1");
        doc1.addField("title","广东某某科技");
        doc1.addField("cat","互联网科技公司，拥有大量高素质人才");

        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField("id","2");
        doc2.addField("title","广西某X工业园");
        doc2.addField("cat","工业园生成产品的喔！");

        SolrInputDocument doc3 = new SolrInputDocument();
        doc3.addField("id","3");
        doc3.addField("title","lifeix");
        doc3.addField("cat","天气变冷了，要记得穿衣服");

        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);
        try {
            server.add(docs);
            server.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
