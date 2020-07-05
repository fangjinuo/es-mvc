package com.jn.esmvc.examples.service.tcp;

import com.jn.esmvc.examples.model.KnowledgeESModel;
import com.jn.esmvc.model.utils.ESModels;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;

public class ESTcpKnowledgeService {

    private TransportClient transportClient;


    public KnowledgeESModel getById(String id){
        String index = ESModels.getIndex(KnowledgeESModel.class);
        String type = ESModels.getType(KnowledgeESModel.class);

        // 方式 1：
        GetRequestBuilder requestBuilder = transportClient.prepareGet(index,type,id);
        ActionFuture<GetResponse> actionFuture = requestBuilder.execute();
        GetResponse response = actionFuture.actionGet();
        // 方式 2：
        GetRequest request = new GetRequest();
        actionFuture= transportClient.get(request);
        response = actionFuture.actionGet();

    }


    @Autowired
    public void setTransportClient(TransportClient transportClient) {
        this.transportClient = transportClient;
    }
}
