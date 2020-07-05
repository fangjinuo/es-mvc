package com.jn.esmvc.service.request.tcp;

import com.jn.esmvc.service.request.action.termvectors.TermVectorsResponse;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.termvectors.TermVectorsRequest;

import java.util.Map;

public class EsTcpRequests {
    public static TermVectorsRequest toEsRequest(com.jn.esmvc.service.request.action.termvectors.TermVectorsRequest from){
        TermVectorsRequest to = new TermVectorsRequest(from.getIndex(),from.getType(),from.getId());
        to.doc(from.getDocBuilder());
        to.routing(from.getRouting());
        to.preference(from.getPreference());
        to.realtime(from.isRealtime());
        to.selectedFields(from.getFields());
        to.positions(from.isRequestPositions());
        to.payloads(from.isRequestPayloads());
        to.offsets(from.isRequestOffsets());
        to.fieldStatistics(from.isRequestFieldStatistics());
        to.termStatistics(from.isRequestTermStatistics());
        to.perFieldAnalyzer(from.getPerFieldAnalyzer());

        Map<String,Integer> filterSettinsMap =  from.getFilterSettings();
        Integer maxNumTerms = filterSettinsMap.get("max_num_terms");
        Integer minTermFreq = filterSettinsMap.get("min_term_freq");
        Integer maxTermFreq = filterSettinsMap.get("max_term_freq");
        Integer minDocFreq =  filterSettinsMap.get("min_doc_freq");
        Integer maxDocFreq = filterSettinsMap.get("max_doc_freq");
        Integer minWordLength = filterSettinsMap.get("min_word_length");
        Integer maxWordLength = filterSettinsMap.get("max_word_length");
        TermVectorsRequest.FilterSettings filterSettings = new TermVectorsRequest.FilterSettings(
                maxNumTerms,
                minTermFreq,
                maxTermFreq,
                minDocFreq,
                maxDocFreq,
                minWordLength,
                maxWordLength
        );
        to.filterSettings(filterSettings);
        return to;
    }
    public static TermVectorsResponse fromEsResponse(org.elasticsearch.action.termvectors.TermVectorsResponse from){
        return null;
    }

    public static class TermVectorsRequestHandleListener implements ActionListener<org.elasticsearch.action.termvectors.TermVectorsResponse>{
        private ActionListener<TermVectorsResponse> delegate;
        public TermVectorsRequestHandleListener( ActionListener<TermVectorsResponse> delegate){
            this.delegate = delegate;
        }

        public ActionListener<TermVectorsResponse> getDelegate() {
            return delegate;
        }

        @Override
        public void onResponse(org.elasticsearch.action.termvectors.TermVectorsResponse termVectorsResponse) {
            TermVectorsResponse response = fromEsResponse(termVectorsResponse);
            delegate.onResponse(response);
        }

        @Override
        public void onFailure(Exception e) {
            delegate.onFailure(e);
        }
    }
}
