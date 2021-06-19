package com.jn.esmvc.service;

import com.jn.easyjson.core.JSON;
import com.jn.easyjson.core.JSONBuilderProvider;
import com.jn.easyjson.core.exclusion.IgnoreAnnotationExclusion;
import com.jn.easyjson.core.exclusion.UnderlineStartedExclusion;
import com.jn.esmvc.model.AbstractESModel;

import java.lang.reflect.Modifier;

public class AbstractESModelService<MODEL extends AbstractESModel> implements IESModelService<MODEL> {
    protected ClientWrapper client;
    protected Class<MODEL> modelClass;
    protected JSON json = JSONBuilderProvider.create()
            .addSerializationExclusion(new IgnoreAnnotationExclusion())
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .addSerializationExclusion(new UnderlineStartedExclusion())
            .build();

    @Override
    public Class<MODEL> getModelClass() {
        return modelClass;
    }

    @Override
    public AbstractESModelService<MODEL> setModelClass(Class<MODEL> modelClass) {
        this.modelClass = modelClass;
        return this;
    }

    @Override
    public ClientWrapper getClient() {
        return client;
    }

    @Override
    public IESModelService setClient(ClientWrapper client) {
        this.client = client;
        return this;
    }

}