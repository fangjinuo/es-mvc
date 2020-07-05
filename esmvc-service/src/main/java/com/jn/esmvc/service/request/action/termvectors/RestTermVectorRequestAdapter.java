package com.jn.esmvc.service.request.action.termvectors;

import com.jn.esmvc.service.request.action.RequestAdapter;
import org.elasticsearch.client.core.TermVectorsRequest;

public class RestTermVectorRequestAdapter implements RequestAdapter<com.jn.esmvc.service.request.action.termvectors.TermVectorsRequest,TermVectorsRequest> {
    @Override
    public TermVectorsRequest apply(com.jn.esmvc.service.request.action.termvectors.TermVectorsRequest from) {
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
