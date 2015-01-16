package com.lhx.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.File;
import java.io.IOException;

/**
 * Created by lhx on 15-1-16 上午10:07
 *
 * @project gitjava
 * @package com.lhx.solrj
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class CreateIndexFromPDF {
    public static void indexFilesSolr(String fileName, String solrId) throws IOException, SolrServerException {
        String urlString = "http://localhost:8080/solr/pdf_core";
        SolrServer solr = new HttpSolrServer(urlString);
        ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/extract");
        String contentType = "application/pdf";
        up.addFile(new File(fileName), contentType);
        up.setParam("literal.id", solrId);
        up.setParam("uprefix","attr_");
        up.setParam("fmap.content","attr_content");
        up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);

        solr.request(up);

        QueryResponse rsp = solr.query(new SolrQuery("*:*"));
        System.out.println(rsp);
    }

    public static void main(String[] args) {
        String fileName = "F:\\Sencha_Touch_2.0用户指南(中文版).pdf";
        String solrId = "Sencha_Touch_2.0用户指南(中文版).pdf";
        try {
            indexFilesSolr(fileName,solrId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
