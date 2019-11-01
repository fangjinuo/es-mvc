/*
 *  Copyright (c) 2016
 *  BES Software Corporation.
 *  All Rights Reserved
 *      Revision History:
 *                                 Modification       Tracking
 *  Author (Email ID)              Date               Number               Description
 *  -------------------------------------------------------------------------------------------
 *  jinuo.fang                     2019-04-12                              Initial version
 *
 */

package com.jn.esmvc.model;

import com.jn.esmvc.model.utils.ESModels;
import com.jn.langx.util.Dates;
import com.jn.langx.util.Strings;
import com.jn.langx.util.id.UuidGenerator;

import java.util.Date;

public class ESModelIdGenerator<MODEL extends AbstractESModel> implements ESDocumentIdGenerator<MODEL> {
    private static final String PATTERN = "yyyyMMddHHmmSS";
    private UuidGenerator uuidGenerator = new UuidGenerator();

    @Override
    public String get() {
        return get(null);
    }

    @Override
    public String get(MODEL model) {
        String id = model.get_id();
        if (Strings.isBlank(id)) {
            String index = ESModels.getIndex(model.getClass());
            String type = ESModels.getType(model.getClass());
            id = index + "_" + type + Dates.format(new Date(), PATTERN) + "_" + uuidGenerator.get();
        }
        return id;
    }
}
