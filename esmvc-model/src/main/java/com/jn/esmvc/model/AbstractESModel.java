/*
 *  Copyright (c) 2016
 *  BES Software Corporation.
 *  All Rights Reserved
 *      Revision History:
 *                                 Modification       Tracking
 *  Author (Email ID)              Date               Number               Description
 *  -------------------------------------------------------------------------------------------
 *  jinuo.fang                     2019-04-18                              Initial version
 *
 */

package com.jn.esmvc.model;

import java.util.Map;

@Type
public abstract class AbstractESModel<MODEL extends AbstractESModel> {
    protected transient String _id;
    protected transient Map<String, HighlightField> highlightFieldMap;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Map<String, HighlightField> getHighlightFieldMap() {
        return highlightFieldMap;
    }

    public void setHighlightFieldMap(Map<String, HighlightField> highlightFieldMap) {
        this.highlightFieldMap = highlightFieldMap;
    }
}
