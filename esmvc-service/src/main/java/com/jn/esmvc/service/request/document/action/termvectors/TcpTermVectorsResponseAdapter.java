package com.jn.esmvc.service.request.document.action.termvectors;

import com.jn.esmvc.service.request.document.action.ResponseAdapter;

public class TcpTermVectorsResponseAdapter implements ResponseAdapter<org.elasticsearch.action.termvectors.TermVectorsResponse,TermVectorsResponse> {
    @Override
    public TermVectorsResponse apply(org.elasticsearch.action.termvectors.TermVectorsResponse input) {
        return null;
    }
}
