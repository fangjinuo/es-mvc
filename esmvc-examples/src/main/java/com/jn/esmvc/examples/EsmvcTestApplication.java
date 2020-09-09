package com.jn.esmvc.examples;

import com.jn.esmvc.service.ESRestClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Map;

@SpringBootApplication
public class EsmvcTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsmvcTestApplication.class, args);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(60000000);
                }catch (Throwable ex){
                    // ignore
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
    @Autowired
    private TransportClient transportClient;

    @Autowired
    private ESRestClient esRestClient;

    @PostConstruct
    public void test(){
        try {
            SearchRequestBuilder srb = transportClient.prepareSearch("sg6-auditlog-2020.09.08");
            SearchResponse searchResponse = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();

            SearchHits hits=searchResponse.getHits();
            for(SearchHit hit:hits){
                System.out.println(hit.getSourceAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            RestHighLevelClient restClient = esRestClient.getRestClient();
            SearchRequest searchRequest = new SearchRequest("sg6-auditlog-2020.09.08");
            SearchResponse searchResponse = restClient.search(searchRequest);

            SearchHits hits = searchResponse.getHits();
            long totalHits = hits.getTotalHits();
            for (SearchHit hit : hits) {
                String index = hit.getIndex(); //获取文档的index
                String type = hit.getType(); //获取文档的type
                String id = hit.getId(); //获取文档的id
                Map<String, Object> sourceMap = hit.getSourceAsMap();
                String sourceString = hit.getSourceAsString(); //获取文档内容，转换为json字符串。

                System.out.println(id+"$$$$$$$$$$"+index+"$$$$$$$$$$$$$$$$$"+type+"&&&&&&&&&&&&&&&&"+sourceString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
