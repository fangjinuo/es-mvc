
package com.jn.esmvc.service;


import com.jn.esmvc.model.AbstractESModel;

import java.io.IOException;
import java.util.List;

public interface ESModelCRUDService<MODEL extends AbstractESModel> extends IESModelService<MODEL> {
    /**
     * Document API: Index API
     *
     * @param model entity
     * @return id
     * @throws IOException throws it when network or tcp error
     */
    String add(MODEL model) throws IOException;

    /**
     * Document API: Get API
     *
     * @param id document's id
     * @return entity
     * @throws IOException throws it when network or tcp error
     */
    MODEL getById(String id) throws IOException;

    /**
     * Document API: Delete API
     *
     * @param id document's id
     * @return whether delete success or not
     * @throws IOException throws it when network or tcp error
     */
    boolean removeById(String id) throws IOException;

    /**
     * Document API: Update API
     *
     * @param id document's id
     * @return whether update success or not
     * @throws IOException throws it when network or tcp error
     */
    boolean updateById(String id, MODEL model) throws IOException;

    /**
     * Document API: Update API ; Get API
     *
     * @param id document's id
     * @return the model after updated
     * @throws IOException throws it when network or tcp error
     */
    MODEL replaceById(String id, MODEL model) throws IOException;

    /**
     * Document API: Update API with upsert
     *
     * @param model entity
     * @return the model after updated
     * @throws IOException throws it when network or tcp error
     */
    MODEL merge(MODEL model) throws IOException;

    /**
     * Document API: Multi Get API
     *
     * @param ids multi document ids
     * @return the model after updated
     * @throws IOException throws it when network or tcp error
     */
    List<MODEL> getByIds(List<String> ids) throws IOException;

    /**
     * Document API: Bulk API + merge()
     *
     * @param models entities
     * @throws IOException throws it when network or tcp error
     */
    void bulkMerge(List<MODEL> models) throws IOException;

}
