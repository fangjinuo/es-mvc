package com.jn.esmvc.service;


import com.jn.esmvc.model.AbstractESModel;

public interface ESModelService<MODEL extends AbstractESModel> extends ESModelCRUDService<MODEL>, ESModelSearchService<MODEL>{
}
