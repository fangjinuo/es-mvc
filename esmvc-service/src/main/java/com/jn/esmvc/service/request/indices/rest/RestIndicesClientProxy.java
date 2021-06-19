package com.jn.esmvc.service.request.indices.rest;

import com.jn.esmvc.service.request.RestClientWrapper;
import com.jn.esmvc.service.request.indices.IndicesClientProxy;

public class RestIndicesClientProxy implements IndicesClientProxy {
    private RestClientWrapper restClient;

    public RestIndicesClientProxy(RestClientWrapper restClient){
        this.restClient = restClient;
    }
}
