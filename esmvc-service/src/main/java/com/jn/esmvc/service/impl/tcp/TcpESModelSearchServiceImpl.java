package com.jn.esmvc.service.impl.tcp;

import com.jn.esmvc.model.AbstractESModel;
import com.jn.esmvc.service.AbstractESModelService;
import com.jn.esmvc.service.ESModelSearchService;
import com.jn.esmvc.service.scroll.ScrollContextCache;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

public class TcpESModelSearchServiceImpl <MODEL extends AbstractESModel> extends AbstractESModelService<MODEL> implements ESModelSearchService<MODEL> {
    @Override
    public void setScrollCache(ScrollContextCache scrollContextCache) {

    }

    @Override
    public void setScrollDuration(long durationInMills) {

    }

    @Override
    public long count(SearchSourceBuilder bodyBuilder) throws IOException {
        return 0;
    }

    @Override
    public List<MODEL> search(SearchSourceBuilder bodyBuilder) throws IOException {
        return null;
    }

    @Override
    public List<MODEL> union(SearchSourceBuilder... bodyBuilder) throws IOException {
        return null;
    }

    @Override
    public List<MODEL> all(SearchSourceBuilder bodyBuilder) throws IOException {
        return null;
    }

    @Override
    public List<MODEL> none(SearchSourceBuilder bodyBuilder) throws IOException {
        return null;
    }

    @Override
    public void clearScroll(String scrollId) {

    }
}
