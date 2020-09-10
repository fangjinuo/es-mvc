package com.jn.esmvc.model.utils;

import com.jn.esmvc.model.*;
import com.jn.langx.util.Preconditions;

public class ESModels {
    public static final String INDEX_DEFAULT_TYPE = "_doc";
    public static final ESModelIdGenerator defaultModelIdGenerator = new ESModelIdGenerator();

    public static String getIndex(Class<? extends AbstractESModel> modelClass) {
        try {
            Index indexAnnotation = modelClass.getAnnotation(Index.class);
            return indexAnnotation.value();
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Can't find an annotation in class [" + modelClass.getCanonicalName() + "]");
        }
    }

    public static String getType(Class<? extends AbstractESModel> modelClass) {
        try {
            Type typeAnnotation = modelClass.getAnnotation(Type.class);
            if (typeAnnotation == null) {
                return INDEX_DEFAULT_TYPE;
            }
            return typeAnnotation.value();
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Can't find an annotation in class [" + modelClass.getCanonicalName() + "]");
        }
    }

    public static <MODEL extends AbstractESModel> String getId(MODEL model) {
        return getId(model, defaultModelIdGenerator);
    }

    public static <MODEL extends AbstractESModel> String getId(MODEL model, ESDocumentIdGenerator<MODEL> documentIdGenerator) {
        Preconditions.checkNotNull(documentIdGenerator);
        return documentIdGenerator.get(model);
    }

}
