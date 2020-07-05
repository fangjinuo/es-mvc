package com.jn.esmvc.service.request.rest;

import org.elasticsearch.client.core.TermVectorsRequest;

public class EsRestRequests {
    public static TermVectorsRequest toEsRequest(com.jn.esmvc.service.request.action.termvectors.TermVectorsRequest from){
        TermVectorsRequest to = new TermVectorsRequest(from.getIndex(), from.getType(), from.getDocBuilder());
        to.setRouting(from.getRouting());
        to.setPreference(from.getPreference());
        to.setRealtime(from.isRealtime());
        to.setFields(from.getFields());
        to.setPositions(from.isRequestPositions());
        to.setPayloads(from.isRequestPayloads());
        to.setOffsets(from.isRequestOffsets());
        to.setFieldStatistics(from.isRequestFieldStatistics());
        to.setTermStatistics(from.isRequestTermStatistics());
        to.setPerFieldAnalyzer(from.getPerFieldAnalyzer());
        to.setFilterSettings(from.getFilterSettings());
        return to;
    }
}
