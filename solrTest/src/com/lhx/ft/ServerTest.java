package com.lhx.ft;

import com.lhx.entity.Index;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lhx on 15-1-16 上午10:05
 *
 * @project gitjava
 * @package com.lhx.ft
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class ServerTest {

    private SolrServer server ;
    private HttpSolrServer httpServer;

    //    private static final String DEFAULT_URL = "http://localhost:8080/solr/ft";
    //第一次
    //private static final String DEFAULT_URL = "http://192.168.199.22:8080/ft_topic_core/";
    //用户
    private static final String DEFAULT_URL = "http://192.168.199.22:8080/sneakrole_core/";
//    private static final String DEFAULT_URL = "http://localhost:8080/solr/collection1";

    @Before
    public void init(){
        server = new HttpSolrServer(DEFAULT_URL);
        httpServer = new HttpSolrServer(DEFAULT_URL);
    }

    @After
    public void destory(){
        server = null ;
        httpServer = null ;
        System.runFinalization();
        System.gc();
    }

    public final void fail(Object o){
        System.out.println(o);
    }

    @Test
    public void server(){
        fail(server) ;
        fail(httpServer);
    }

    public void query(String query){
        SolrParams params = new SolrQuery(query);
        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addDoc() {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 1);
        doc.addField("name", "Solr Input Document");
        doc.addField("manu", "This is a doc content");

        try {
            UpdateResponse response = server.add(doc);
            fail(server.commit());
            fail(response);
            fail("query time:" + response.getQTime());
            fail("Elapsed Time:" + response.getElapsedTime());
            fail("status:" + response.getStatus());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        query("name:solr");
    }

    @Test
    public void addDocs() {

        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 2);
        doc.addField("name", "Solr Input Documents 1");
        doc.addField("manu", "this is SolrInputDocuments 2 content");
        docs.add(doc);

        doc = new SolrInputDocument();
        doc.addField("id", 3);
        doc.addField("name", "Solr Input Documents 2");
        doc.addField("manu", "this is SolrInputDocuments 3 content");
        docs.add(doc);
        try {
            //add docs
            UpdateResponse response = server.add(docs);
            //commit后才保存到索引库
            fail(server.commit());
            fail(response);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        query("solr");
    }

    @Test
    public void addBean(){
        Index index = new Index();
        index.setId("4");
        index.setName("add bean index");
        index.setManu("index bean manu");
        index.setCat(new String[]{"a1","b2"});
        try {
            UpdateResponse response = server.addBean(index);
            fail(server.commit());
            fail(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        query("index");
    }

    @Test
    public void addBeans(){
        Index index = new Index();
        index.setId("6");
        index.setName("add beans index 1");
        index.setManu("index beans manu 1");
        index.setCat(new String[]{"a","v"});

        List<Index> indexs = new ArrayList<Index>();
        indexs.add(index);

        index = new Index();
        index.setId("5");
        index.setName("add beans index 2");
        index.setManu("index beans manu 2");
        index.setCat(new String[]{"af","fvf"});
        try {
            UpdateResponse response = server.addBeans(indexs);
            fail(server.commit());
            fail(response);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void remove(){
        try {
            server.deleteById("1");
            server.commit();
            query("id:1");

            List<String> ids = new ArrayList<String>();
            ids.add("2");
            ids.add("3");
            server.deleteById(ids);
            server.commit(true, true);
            query("id:3 id:2");

            server.deleteByQuery("id:4 id:6");
            server.commit(true, true);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryAll(){
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("q","accountName:立方");
        params.set("start",0);
        params.set("rows",10);

        params.set("sort","score desc");
//        params.set("f1","topic_name,visit_num");
        params.set("f1","*,score");

        try {
            QueryResponse response = server.query(params);
            SolrDocumentList list = response.getResults();
            for (int i = 0; i < list.size(); i++) {
                SolrDocument document = list.get(i);
//                fail(document.getFieldValue("topicName"));
//                fail(document.getFieldValue("visitNum"));
                fail(document.getFieldValue("l99NO"));
                fail(document.getFieldValue("accountName"));
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void otherMethod() {
        fail(server.getBinder());
        try {
            fail(server.optimize());//合并索引文件，可以优化索引、提供性能，但需要一定的时间
            fail(server.ping());//ping服务器是否连接成功

            Index index = new Index();
            index.setId("299");
            index.setName("add bean index199");
            index.setManu("index bean manu199");
            index.setCat(new String[] { "a199", "b199" });

            UpdateResponse response = server.addBean(index);
            fail("response: " + response);

            queryAll();

            //回滚掉之前的操作，rollback addBean operation
            fail("rollback: " + server.rollback());

            //提交操作，提交后无法回滚之前操作；发现addBean没有成功添加索引
            fail("commit: " + server.commit());

            queryAll();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test

    public void queryCase() {

        //AND 并且

        SolrQuery params = new SolrQuery("name:apple AND manu:inc");
        //OR 或者
        params.setQuery("name:apple OR manu:apache");
        //空格 等同于 OR
        params.setQuery("name:server manu:dell");



        //params.setQuery("name:solr - manu:inc");

        //params.setQuery("name:server + manu:dell");



        //查询name包含solr apple

        params.setQuery("name:solr,apple");

        //manu不包含inc

        params.setQuery("name:solr,apple NOT manu:inc");



        //50 <= price <= 200

        params.setQuery("price:[50 TO 200]");

        params.setQuery("popularity:[5 TO 6]");

        //params.setQuery("price:[50 TO 200] - popularity:[5 TO 6]");

        //params.setQuery("price:[50 TO 200] + popularity:[5 TO 6]");



        //50 <= price <= 200 AND 5 <= popularity <= 6

        params.setQuery("price:[50 TO 200] AND popularity:[5 TO 6]");

        params.setQuery("price:[50 TO 200] OR popularity:[5 TO 6]");



        //过滤器查询，可以提高性能 filter 类似多个条件组合，如and

        //params.addFilterQuery("id:VA902B");

        //params.addFilterQuery("price:[50 TO 200]");

        //params.addFilterQuery("popularity:[* TO 5]");

        //params.addFilterQuery("weight:*");

        //0 < popularity < 6  没有等于

        //params.addFilterQuery("popularity:{0 TO 6}");



        //排序

        params.addSortField("id", SolrQuery.ORDER.asc);


        //分页：start开始页，rows每页显示记录条数

        //params.add("start", "0");

        //params.add("rows", "200");

        //params.setStart(0);

        //params.setRows(200);



        //设置高亮

        params.setHighlight(true); // 开启高亮组件

        params.addHighlightField("name");// 高亮字段

        params.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀

        params.setHighlightSimplePost("</font>");//后缀

        params.setHighlightSnippets(1);//结果分片数，默认为1

        params.setHighlightFragsize(1000);//每个分片的最大长度，默认为100



        //分片信息

        params.setFacet(true)

                .setFacetMinCount(1)

                .setFacetLimit(5)//段

                .addFacetField("name")//分片字段

                .addFacetField("inStock");



        //params.setQueryType("");



        try {

            QueryResponse response = server.query(params);



        /*List<Index> indexs = response.getBeans(Index.class);

        for (int i = 0; i < indexs.size(); i++) {

            fail(indexs.get(i));

        }*/



            //输出查询结果集

            SolrDocumentList list = response.getResults();

            fail("query result nums: " + list.getNumFound());

            for (int i = 0; i < list.size(); i++) {

                fail(list.get(i));

            }



            //输出分片信息

            List<FacetField> facets = response.getFacetFields();

            for (FacetField facet : facets) {

                fail(facet);

                List<FacetField.Count> facetCounts = facet.getValues();

                for (FacetField.Count count : facetCounts) {

                    System.out.println(count.getName() + ": " + count.getCount());

                }

            }

        } catch (SolrServerException e) {

            e.printStackTrace();

        }

    }


    @Test
    public void facetQueryCase() {
        SolrQuery params = new SolrQuery("*:*");

        //排序
        params.addSortField("id", SolrQuery.ORDER.asc);


        params.setStart(0);
        params.setRows(200);


        //Facet为solr中的层次分类查询
        //分片信息
        params.setFacet(true)
                .setQuery("*:*")
                .setFacetMinCount(1)
                .setFacetLimit(5)//段
                        //.setFacetPrefix("electronics", "cat")
                .setFacetPrefix("cor")//查询manu、name中关键字前缀是cor的
                .addFacetField("manu")
                .addFacetField("name");//分片字段
        try {
            QueryResponse response = server.query(params);

            //输出查询结果集
            SolrDocumentList list = response.getResults();
            fail("Query result nums: " + list.getNumFound());
            for (int i = 0; i < list.size(); i++) {
                fail(list.get(i));
            }


            fail("All facet filed result: ");
            //输出分片信息
            List<FacetField> facets = response.getFacetFields();
            for (FacetField facet : facets) {
                fail(facet);
                List<FacetField.Count> facetCounts = facet.getValues();
                for (FacetField.Count count : facetCounts) {
                    //关键字 - 出现次数
                    fail(count.getName() + ": " + count.getCount());
                }
            }

            fail("Search facet [name] filed result: ");
            //输出分片信息
            FacetField facetField = response.getFacetField("name");

            List<FacetField.Count> facetFields = facetField.getValues();
            for (FacetField.Count count : facetFields) {
                //关键字 - 出现次数
                fail(count.getName() + ": " + count.getCount());
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
