/*
 *  Copyright (c) 2016
 *  BES Software Corporation.
 *  All Rights Reserved
 *      Revision History:
 *                                 Modification       Tracking
 *  Author (Email ID)              Date               Number               Description
 *  -------------------------------------------------------------------------------------------
 *  jinuo.fang                     2019-04-15                              Initial version
 *
 */

package com.jn.esmvc.service;

import com.jn.easyjson.core.JSON;
import com.jn.easyjson.core.JSONBuilderProvider;
import com.jn.easyjson.core.exclusion.IgnoreAnnotationExclusion;
import com.jn.easyjson.core.exclusion.UnderlineStartedExclusion;
import com.jn.esmvc.model.AbstractESModel;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class AbstractESModelService<MODEL extends AbstractESModel> implements IESModelService<MODEL> {
    protected ESRestClient client;
    protected Class<MODEL> modelClass;
    protected JSON json = JSONBuilderProvider.create()
            .addSerializationExclusion(new IgnoreAnnotationExclusion())
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .addSerializationExclusion(new UnderlineStartedExclusion())
            .build();

    @Override
    public ESRestClient getClient() {
        return client;
    }

    @Override
    public AbstractESModelService<MODEL> setClient(ESRestClient client) {
        this.client = client;
        return this;
    }

    @Override
    public Class<MODEL> getModelClass() {
        return modelClass;
    }

    @Override
    public AbstractESModelService<MODEL> setModelClass(Class<MODEL> modelClass) {
        this.modelClass = modelClass;
        return this;
    }

    protected final MODEL asModel(GetResult result) {
        if (!result.isSourceEmpty()) {
            MODEL model = asModel(result.sourceAsString());
            model.set_id(result.getId());
            return model;
        }
        return null;
    }

    protected final MODEL asModel(GetResponse response) {
        if (response.isExists() && !response.isSourceEmpty()) {
            MODEL model = asModel(response.getSourceAsString());
            model.set_id(response.getId());
            return model;
        }
        return null;
    }

    protected final MODEL asModel(String source) {
        return json.fromJson(source, modelClass);
    }

    protected final MODEL asModel(SearchHit hit) {
        if (hit.hasSource()) {
            MODEL model = asModel(hit.getSourceAsString());
            model.set_id(hit.getId());
            Map<String, HighlightField> highlightFieldsMap = hit.getHighlightFields();
            if (highlightFieldsMap != null && !highlightFieldsMap.isEmpty()) {
                final Map<String, com.jn.esmvc.model.HighlightField> highlightFieldMap = new HashMap<String, com.jn.esmvc.model.HighlightField>(highlightFieldsMap.size());

                highlightFieldsMap.forEach((key, highlightFields) -> {
                    com.jn.esmvc.model.HighlightField highlightField = new com.jn.esmvc.model.HighlightField(highlightFields.name());
                    Text[] fragments = highlightFields.fragments();
                    for (Text fragment : fragments) {
                        if (fragment.hasString()) {
                            highlightField.addFragment(fragment.string());
                        }
                    }
                    highlightFieldMap.put(key, highlightField);
                });
                model.setHighlightFieldMap(highlightFieldMap);
            }
            return model;
        }
        return null;
    }
}
