package com.jn.esmvc.service;

import com.jn.langx.util.Preconditions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class ESRestClient {
    private RestHighLevelClient restClient;
    private RequestOptions requestOptions = RequestOptions.DEFAULT;

    public ESRestClient(RestHighLevelClient restClient) {
        this.restClient = restClient;
    }

    public RestHighLevelClient getRestClient() {
        return restClient;
    }

    public void setRestClient(RestHighLevelClient restClient) {
        this.restClient = restClient;
    }

    public RequestOptions getRequestOptions() {
        return requestOptions;
    }

    public void setRequestOptions(RequestOptions requestOptions) {
        Preconditions.checkNotNull(requestOptions);
        this.requestOptions = requestOptions;
    }
}
