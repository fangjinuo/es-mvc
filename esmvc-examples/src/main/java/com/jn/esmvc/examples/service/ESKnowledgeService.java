package com.jn.esmvc.examples.service;

import com.jn.esmvc.examples.model.KnowledgeESModel;
import com.jn.esmvc.examples.service.impl.ESModelServiceImpl;
import com.jn.esmvc.examples.service.scroll.ScrollContextCache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ESKnowledgeService extends ESModelServiceImpl<KnowledgeESModel> implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        setClient(client).setModelClass(KnowledgeESModel.class);
    }

    @Autowired
    @Override
    public ESKnowledgeService setClient(ESRestClient client) {
        super.setClient(client);
        return this;
    }

    @Autowired
    @Override
    public void setScrollCache(ScrollContextCache scrollContextCache) {
        super.setScrollCache(scrollContextCache);
        setScrollDuration(scrollContextCache.getExpireInSeconds() * 1000 + 5 * 1000);
    }
}
