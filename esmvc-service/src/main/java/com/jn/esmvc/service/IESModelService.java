package com.jn.esmvc.service;

import com.jn.esmvc.model.AbstractESModel;

public interface IESModelService<MODEL extends AbstractESModel> {
    ESRestClient getClient();

    AbstractESModelService<MODEL> setClient(ESRestClient client);

    Class<MODEL> getModelClass();

    AbstractESModelService<MODEL> setModelClass(Class<MODEL> modelClass);
}
