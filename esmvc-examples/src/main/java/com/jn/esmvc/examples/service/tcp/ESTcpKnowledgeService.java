package com.jn.esmvc.examples.service.tcp;

import com.jn.esmvc.examples.model.KnowledgeESModel;
import com.jn.esmvc.service.impl.ESModelServiceImpl;
import com.jn.esmvc.service.tcp.TcpClientWrapper;
import com.jn.esmvc.service.scroll.ScrollContextCache;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ESTcpKnowledgeService extends ESModelServiceImpl<KnowledgeESModel> implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        setClient(client).setModelClass(KnowledgeESModel.class);
    }

    /*
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

        // 方式 3：

    }
    */

    @Autowired
    public void setTransportClient(TcpClientWrapper transportClient) {
        setClient(transportClient);
    }

    @Autowired
    @Override
    @Qualifier("scrollContextCache")
    public void setScrollCache(ScrollContextCache scrollContextCache) {
        super.setScrollCache(scrollContextCache);
        setScrollDuration(scrollContextCache.getExpireInSeconds() * 1000 + 5 * 1000);
    }
}
