package com.jn.esmvc.service;

import com.jn.esmvc.model.AbstractESModel;

public interface IESModelService<MODEL extends AbstractESModel> {
    Class<MODEL> getModelClass();

    IESModelService setModelClass(Class<MODEL> modelClass);

    ClientWrapper getClient();
    IESModelService setClient(ClientWrapper clientHolder);
}
