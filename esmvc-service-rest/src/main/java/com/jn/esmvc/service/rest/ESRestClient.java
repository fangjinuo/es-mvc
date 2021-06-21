package com.jn.esmvc.service.rest;

import com.jn.langx.util.Preconditions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

@Deprecated
public class ESRestClient {
    private RestHighLevelClient client;
    private RequestOptions requestOptions = RequestOptions.DEFAULT;

    public ESRestClient(RestHighLevelClient restClient) {
        setRestClient(restClient);
    }

    public RestHighLevelClient getRestClient() {
        return client;
    }

    public void setRestClient(RestHighLevelClient restClient) {
        this.client = restClient;
    }

    public RequestOptions getRequestOptions() {
        return requestOptions;
    }

    public void setRequestOptions(RequestOptions requestOptions) {
        Preconditions.checkNotNull(requestOptions);
        this.requestOptions = requestOptions;
    }
}
