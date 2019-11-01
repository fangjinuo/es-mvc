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

import com.jn.esmvc.model.AbstractESModel;

public interface IESModelService<MODEL extends AbstractESModel> {
    ESRestClient getClient();

    AbstractESModelService<MODEL> setClient(ESRestClient client);

    Class<MODEL> getModelClass();

    AbstractESModelService<MODEL> setModelClass(Class<MODEL> modelClass);
}
