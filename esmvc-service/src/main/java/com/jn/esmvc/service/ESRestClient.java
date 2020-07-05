package com.jn.esmvc.service;

import com.jn.langx.util.Preconditions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class ESRestClient extends ClientHolder<RestHighLevelClient> {
    private RequestOptions requestOptions = RequestOptions.DEFAULT;

    public ESRestClient(RestHighLevelClient restClient) {
        setRestClient(restClient);
    }

    public RestHighLevelClient getRestClient() {
        return get();
    }

    public void setRestClient(RestHighLevelClient restClient) {
        set(restClient);
    }

    public RequestOptions getRequestOptions() {
        return requestOptions;
    }

    public void setRequestOptions(RequestOptions requestOptions) {
        Preconditions.checkNotNull(requestOptions);
        this.requestOptions = requestOptions;
    }
}
