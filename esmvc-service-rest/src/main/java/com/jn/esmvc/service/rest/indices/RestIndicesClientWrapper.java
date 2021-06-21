package com.jn.esmvc.service.rest.indices;

import com.jn.esmvc.service.request.indices.IndicesClientWrapper;
import com.jn.esmvc.service.rest.RestClientWrapper;

public class RestIndicesClientWrapper implements IndicesClientWrapper<RestClientWrapper> {
    private RestClientWrapper restClient;

    public RestClientWrapper getClientWrapper() {
        return restClient;
    }

    public RestIndicesClientWrapper(RestClientWrapper restClient){
        this.restClient = restClient;
    }
}
