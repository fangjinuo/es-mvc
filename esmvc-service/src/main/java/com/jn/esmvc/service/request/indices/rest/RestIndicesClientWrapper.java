package com.jn.esmvc.service.request.indices.rest;

import com.jn.esmvc.service.request.RestClientWrapper;
import com.jn.esmvc.service.request.indices.IndicesClientWrapper;

public class RestIndicesClientWrapper implements IndicesClientWrapper<RestClientWrapper> {
    private RestClientWrapper restClient;

    public RestClientWrapper getClientWrapper() {
        return restClient;
    }

    public RestIndicesClientWrapper(RestClientWrapper restClient){
        this.restClient = restClient;
    }
}
