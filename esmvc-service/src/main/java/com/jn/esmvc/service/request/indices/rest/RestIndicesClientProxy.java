package com.jn.esmvc.service.request.indices.rest;

import com.jn.esmvc.service.request.RestClientProxy;
import com.jn.esmvc.service.request.indices.IndicesClientProxy;

public class RestIndicesClientProxy implements IndicesClientProxy {
    private RestClientProxy restClient;

    public RestIndicesClientProxy(RestClientProxy restClient){
        this.restClient = restClient;
    }
}
