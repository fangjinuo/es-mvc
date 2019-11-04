package com.jn.esmvc.model;


import java.util.Map;

public class Knowledge implements Entity<String> {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private EditInfo createInfo;
    private EditInfo lastModifyInfo;
    private String keywords;
    private String labels;
    private String content;
    private Map<String, HighlightField> highlightFieldMap;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EditInfo getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(EditInfo createInfo) {
        this.createInfo = createInfo;
    }

    public EditInfo getLastModifyInfo() {
        return lastModifyInfo;
    }

    public void setLastModifyInfo(EditInfo lastModifyInfo) {
        this.lastModifyInfo = lastModifyInfo;
    }

    @Override
    public String getId0() {
        return id;
    }

    @Override
    public void setId0(String s) {
        id = s;
    }

    @Override
    public Class<String> getId0Type() {
        return String.class;
    }

    public Map<String, HighlightField> getHighlightFieldMap() {
        return highlightFieldMap;
    }

    public void setHighlightFieldMap(Map<String, HighlightField> highlightFieldMap) {
        this.highlightFieldMap = highlightFieldMap;
    }
}
