package com.jn.esmvc.service.request.document.action.termvectors;

import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.util.Map;

public class TermVectorsRequest extends ActionRequest {
    private String index;
    private String type;
    private String id = null;
    private XContentBuilder docBuilder = null;

    private String routing = null;
    private String preference = null;
    private boolean realtime = true;
    private String[] fields = null;
    private boolean requestPositions = true;
    private boolean requestPayloads = true;
    private boolean requestOffsets = true;
    private boolean requestFieldStatistics = true;
    private boolean requestTermStatistics = false;
    private Map<String, String> perFieldAnalyzer = null;
    private Map<String, Integer> filterSettings = null;

    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    public TermVectorsRequest(String index, String type, XContentBuilder docBuilder) {
        this.index = index;
        this.type = type;
        this.docBuilder = docBuilder;
    }

    public TermVectorsRequest(String index, String type, String docId) {
        this.index = index;
        this.type = type;
        this.id = docId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public XContentBuilder getDocBuilder() {
        return docBuilder;
    }

    public void setDocBuilder(XContentBuilder docBuilder) {
        this.docBuilder = docBuilder;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public boolean isRealtime() {
        return realtime;
    }

    public void setRealtime(boolean realtime) {
        this.realtime = realtime;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public boolean isRequestPositions() {
        return requestPositions;
    }

    public void setRequestPositions(boolean requestPositions) {
        this.requestPositions = requestPositions;
    }

    public boolean isRequestPayloads() {
        return requestPayloads;
    }

    public void setRequestPayloads(boolean requestPayloads) {
        this.requestPayloads = requestPayloads;
    }

    public boolean isRequestOffsets() {
        return requestOffsets;
    }

    public void setRequestOffsets(boolean requestOffsets) {
        this.requestOffsets = requestOffsets;
    }

    public boolean isRequestFieldStatistics() {
        return requestFieldStatistics;
    }

    public void setRequestFieldStatistics(boolean requestFieldStatistics) {
        this.requestFieldStatistics = requestFieldStatistics;
    }

    public boolean isRequestTermStatistics() {
        return requestTermStatistics;
    }

    public void setRequestTermStatistics(boolean requestTermStatistics) {
        this.requestTermStatistics = requestTermStatistics;
    }

    public Map<String, String> getPerFieldAnalyzer() {
        return perFieldAnalyzer;
    }

    public void setPerFieldAnalyzer(Map<String, String> perFieldAnalyzer) {
        this.perFieldAnalyzer = perFieldAnalyzer;
    }

    public Map<String, Integer> getFilterSettings() {
        return filterSettings;
    }

    public void setFilterSettings(Map<String, Integer> filterSettings) {
        this.filterSettings = filterSettings;
    }
}
