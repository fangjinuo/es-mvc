package com.jn.esmvc.service.rest.document.action.termvectors;

import com.jn.esmvc.service.request.document.action.ResponseAdapter;
import com.jn.esmvc.service.request.document.action.termvectors.TermVectorsResponse;

public class RestTermVectorsResponseAdapter implements ResponseAdapter<org.elasticsearch.client.core.TermVectorsResponse, TermVectorsResponse> {
    @Override
    public TermVectorsResponse apply(org.elasticsearch.client.core.TermVectorsResponse input) {
        return null;
    }
}
