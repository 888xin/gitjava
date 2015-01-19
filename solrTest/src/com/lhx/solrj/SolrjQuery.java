package com.lhx.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * Created by lhx on 15-1-16 上午10:08
 *
 * @project gitjava
 * @package com.lhx.solrj
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class SolrjQuery {

    public static void main(String[] args) {
        String url = "http://localhost:8080/solr";
        SolrServer server = new HttpSolrServer(url);
        SolrQuery query = new SolrQuery("title:广东");
        try {
            QueryResponse response = server.query(query);
            SolrDocumentList docs = response.getResults();
            System.out.println("文档个数：" + docs.getNumFound());
            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc : docs) {
                System.out.println("id" + doc.getFieldValue("id"));
                System.out.println("name" + doc.getFieldValue("title"));
                System.out.println("==================");
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
