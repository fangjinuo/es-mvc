package com.jn.esmvc.service.request.rest;

import com.jn.esmvc.service.request.action.count.CountResponse;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;

public class EsRestRequests {

    public static TermVectorsRequest toEsRequest(com.jn.esmvc.service.request.action.termvectors.TermVectorsRequest from) {
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

    public static CountRequest toEsRequest(com.jn.esmvc.service.request.action.count.CountRequest from){
        CountRequest to = new CountRequest();
        to.indices(from.indices());
        to.types(from.types());
        to.indicesOptions(from.indicesOptions());
        to.preference(from.preference());
        to.minScore(from.minScore());
        to.routing(from.routing());
        to.source(from.source());
        to.terminateAfter(from.terminateAfter());
        return to;
    }

}
