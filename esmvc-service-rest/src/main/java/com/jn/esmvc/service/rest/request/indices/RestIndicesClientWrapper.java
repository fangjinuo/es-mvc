package com.jn.esmvc.service.rest.request.indices;

import com.jn.esmvc.service.request.indices.IndicesClientWrapper;
import com.jn.esmvc.service.rest.RestClientWrapper;
import org.elasticsearch.client.RequestOptions;

public class RestIndicesClientWrapper implements IndicesClientWrapper<RestClientWrapper, RequestOptions> {
    private RestClientWrapper restClient;

    public RestClientWrapper getClientWrapper() {
        return restClient;
    }

    public RestIndicesClientWrapper(RestClientWrapper restClient){
        this.restClient = restClient;
    }
}
