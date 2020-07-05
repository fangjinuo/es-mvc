package com.jn.esmvc.service.request.action.termvectors;

import com.jn.esmvc.service.request.action.ResponseAdapter;

public class RestTermVectorsResponseAdapter implements ResponseAdapter<org.elasticsearch.client.core.TermVectorsResponse,TermVectorsResponse> {
    @Override
    public TermVectorsResponse apply(org.elasticsearch.client.core.TermVectorsResponse input) {
        return null;
    }
}
