/*
 *  Copyright (c) 2016
 *  BES Software Corporation.
 *  All Rights Reserved
 *      Revision History:
 *                                 Modification       Tracking
 *  Author (Email ID)              Date               Number               Description
 *  -------------------------------------------------------------------------------------------
 *  jinuo.fang                     2019-04-11                              Initial version
 *
 */

package com.jn.easyjson.core.exclusion;

import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.function.Predicate;
import com.jn.langx.util.reflect.FieldAttributes;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ModifierExclusion implements Exclusion {
    private List<Integer> excludedModifiers = new ArrayList<Integer>();

    public ModifierExclusion() {
        excludedModifiers.add(Modifier.TRANSIENT);
    }

    public void addModifier(int mod) {
        excludedModifiers.add(mod);
    }

    public void removeModifier(int mod) {
        excludedModifiers.remove(mod);
    }

    @Override
    public boolean shouldSkipField(final FieldAttributes fieldAttributes, boolean serialize) {
        return Collects.anyMatch(excludedModifiers, new Predicate<Integer>() {
            @Override
            public boolean test(Integer excludeModifier) {
                return fieldAttributes.hasModifier(excludeModifier);
            }
        });
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass, boolean serialize) {
        return false;
    }
}
