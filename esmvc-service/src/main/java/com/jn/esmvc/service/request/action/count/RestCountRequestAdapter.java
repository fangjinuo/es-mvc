package com.jn.esmvc.service.request.action.count;

import com.jn.esmvc.service.request.action.RequestAdapter;
import org.elasticsearch.client.core.CountRequest;

public class RestCountRequestAdapter implements RequestAdapter<com.jn.esmvc.service.request.action.count.CountRequest,CountRequest> {
    @Override
    public CountRequest apply(com.jn.esmvc.service.request.action.count.CountRequest from) {
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
