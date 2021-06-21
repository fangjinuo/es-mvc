package com.jn.esmvc.examples.service.tcp;

import com.jn.esmvc.examples.model.KnowledgeESModel;
import com.jn.esmvc.examples.service.ESKnowledgeService;
import com.jn.esmvc.service.tcp.TcpClientWrapper;
import com.jn.esmvc.service.scroll.ScrollContextCache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ESTcpKnowledgeService extends ESKnowledgeService implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        setClient(client).setModelClass(KnowledgeESModel.class);
    }

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
