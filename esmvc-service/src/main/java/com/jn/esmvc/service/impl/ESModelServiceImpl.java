package com.jn.esmvc.service.impl;

import com.jn.esmvc.model.AbstractESModel;
import com.jn.esmvc.service.*;
import com.jn.esmvc.service.request.RestClientProxy;
import com.jn.esmvc.service.scroll.ScrollContextCache;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

public class ESModelServiceImpl<MODEL extends AbstractESModel> extends AbstractESModelService<MODEL> implements ESModelService<MODEL> {

    private ESModelCRUDService<MODEL> crudService;
    private ESModelSearchService<MODEL> searchService;

    public ESModelServiceImpl() {
        crudService = new ESModelCRUDServiceImpl<>();
        searchService = new ESModelSearchServiceImpl<>();
    }


    @Override
    public AbstractESModelService<MODEL> setClient(ClientProxy client) {
        super.setClient(client);
        crudService.setClient(client);
        searchService.setClient(client);
        return this;
    }

    public AbstractESModelService<MODEL> setClient(ESRestClient client) {
        super.setClient(RestClientProxy.fromESRestClient(client));
        return this;
    }

    @Override
    public AbstractESModelService<MODEL> setModelClass(Class<MODEL> modelClass) {
        super.setModelClass(modelClass);
        crudService.setModelClass(modelClass);
        searchService.setModelClass(modelClass);
        return this;
    }

    @Override
    public void setScrollCache(ScrollContextCache scrollContextCache) {
        searchService.setScrollCache(scrollContextCache);
    }

    @Override
    public void setScrollDuration(long durationInMills) {
        searchService.setScrollDuration(durationInMills);
    }

    @Override
    public String add(MODEL model) throws IOException {
        return crudService.add(model);
    }

    @Override
    public MODEL getById(String id) throws IOException {
        return crudService.getById(id);
    }

    @Override
    public boolean removeById(String id) throws IOException {
        return crudService.removeById(id);
    }

    @Override
    public boolean updateById(String id, MODEL model) throws IOException {
        return crudService.updateById(id, model);
    }

    @Override
    public MODEL replaceById(String id, MODEL model) throws IOException {
        return crudService.replaceById(id, model);
    }

    @Override
    public MODEL merge(MODEL model) throws IOException {
        return crudService.merge(model);
    }

    @Override
    public List<MODEL> getByIds(List<String> ids) throws IOException {
        return crudService.getByIds(ids);
    }

    @Override
    public void bulkMerge(List<MODEL> models) throws IOException {
        crudService.bulkMerge(models);
    }

    @Override
    public long count(SearchSourceBuilder bodyBuilder) throws IOException {
        return searchService.count(bodyBuilder);
    }

    @Override
    public List<MODEL> search(SearchSourceBuilder bodyBuilder) throws IOException {
        return searchService.search(bodyBuilder);
    }

    @Override
    public List<MODEL> union(SearchSourceBuilder... bodyBuilder) throws IOException {
        return searchService.union(bodyBuilder);
    }

    @Override
    public List<MODEL> all(SearchSourceBuilder bodyBuilder) throws IOException {
        return searchService.all(bodyBuilder);
    }

    @Override
    public List<MODEL> none(SearchSourceBuilder bodyBuilder) throws IOException {
        return searchService.none(bodyBuilder);
    }

    @Override
    public void clearScroll(String scrollId) {
        searchService.clearScroll(scrollId);
    }
}
