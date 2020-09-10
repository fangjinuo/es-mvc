package com.jn.esmvc.model.utils;

import com.jn.esmvc.model.ESDocumentIdGenerator;
import com.jn.esmvc.model.ESModelIdGenerator;
import com.jn.esmvc.model.Index;
import com.jn.esmvc.model.Type;
import com.jn.esmvc.model.AbstractESModel;
import com.jn.langx.util.Preconditions;

public class ESModels {
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
            return typeAnnotation.value();
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Can't find an annotation in class [" + modelClass.getCanonicalName() + "]");
        }
    }

    public static <MODEL extends AbstractESModel>String getId(MODEL model){
        return getId(model, defaultModelIdGenerator);
    }

    public static <MODEL extends AbstractESModel>String getId(MODEL model, ESDocumentIdGenerator<MODEL> documentIdGenerator ){
        Preconditions.checkNotNull(documentIdGenerator);
        return documentIdGenerator.get(model);
    }

}
