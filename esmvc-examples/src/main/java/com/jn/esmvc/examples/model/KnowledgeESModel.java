package com.jn.esmvc.examples.model;


import com.jn.esmvc.model.AbstractESModel;
import com.jn.esmvc.model.Index;

@Index("knowledge")
public class KnowledgeESModel extends AbstractESModel<KnowledgeESModel> {
    public static final KnowledgeESModel DEFAULT = new KnowledgeESModel();
    private String title;
    private String keywords;
    private String labels;
    private EditInfo createInfo;
    private EditInfo lastModifierInfo;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public EditInfo getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(EditInfo createInfo) {
        this.createInfo = createInfo;
    }

    public EditInfo getLastModifierInfo() {
        return lastModifierInfo;
    }

    public void setLastModifierInfo(EditInfo lastModifierInfo) {
        this.lastModifierInfo = lastModifierInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
