package com.jn.esmvc.service.request.rest;

import org.elasticsearch.action.ActionListener;
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

    public static com.jn.esmvc.service.request.action.termvectors.TermVectorsResponse fromEsResponse(TermVectorsResponse response) {
        // return new TermVectorsRequest();
        return null;
    }

    public static class TermVectorsRequestHandleListener implements ActionListener<TermVectorsResponse> {
        private ActionListener<com.jn.esmvc.service.request.action.termvectors.TermVectorsResponse> delegate;

        public TermVectorsRequestHandleListener(ActionListener<com.jn.esmvc.service.request.action.termvectors.TermVectorsResponse> delegate) {
            this.delegate = delegate;
        }

        public ActionListener<com.jn.esmvc.service.request.action.termvectors.TermVectorsResponse> getDelegate() {
            return delegate;
        }

        @Override
        public void onResponse(TermVectorsResponse response) {
            com.jn.esmvc.service.request.action.termvectors.TermVectorsResponse r = fromEsResponse(response);
            delegate.onResponse(r);
        }

        @Override
        public void onFailure(Exception e) {
            delegate.onFailure(e);
        }
    }
}
