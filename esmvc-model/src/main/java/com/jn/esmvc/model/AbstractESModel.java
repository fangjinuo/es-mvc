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
