package com.jn.esmvc.model;

import com.jn.esmvc.model.utils.ESModels;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Type {
    String value() default ESModels.INDEX_DEFAULT_TYPE;
}
