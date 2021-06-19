package com.jn.esmvc.service.request.indices.rest;

import com.jn.esmvc.service.request.RestClientWrapper;
import com.jn.esmvc.service.request.indices.IndicesClientWrapper;

public class RestIndicesClientWrapper implements IndicesClientWrapper {
    private RestClientWrapper restClient;

    public RestIndicesClientWrapper(RestClientWrapper restClient){
        this.restClient = restClient;
    }
}
