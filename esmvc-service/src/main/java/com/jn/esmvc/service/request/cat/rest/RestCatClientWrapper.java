package com.jn.esmvc.service.request.cat.rest;

import com.jn.esmvc.service.request.RestClientWrapper;
import com.jn.esmvc.service.request.cat.CatClientWrapper;
import com.jn.esmvc.service.request.cat.action.CatNodesRequest;
import com.jn.esmvc.service.request.cat.action.CatNodesResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.CheckedFunction;
import org.elasticsearch.common.xcontent.XContentParser;

import java.io.IOException;
import java.util.List;

public class RestCatClientWrapper implements CatClientWrapper<RestClientWrapper, RequestOptions> {
    private RestClientWrapper restClientWrapper;

    public RestCatClientWrapper(RestClientWrapper restClient) {
        this.restClientWrapper = restClient;
    }

    @Override
    public RestClientWrapper getClientWrapper() {
        return restClientWrapper;
    }

    private RestHighLevelClient getHighLevelClient() {
        return restClientWrapper.get();
    }

    private RestClient getLowLevelClient() {
        return getHighLevelClient().getLowLevelClient();
    }

    @Override
    public CatNodesResponse nodes(RequestOptions requestOptions, CatNodesRequest request) {
        RequestOptions options = restClientWrapper.mergeRequestOptions(requestOptions);

        CatNodesResponse catNodesResponse = getClientWrapper().performRequestAndParseEntity(request,
                new CheckedFunction<CatNodesRequest, Request, IOException>() {
                    @Override
                    public Request apply(CatNodesRequest request) throws IOException {
                        return null;
                    }
                }, options,
                new CheckedFunction<XContentParser, CatNodesResponse, IOException>() {
                    @Override
                    public CatNodesResponse apply(XContentParser xContentParser) throws IOException {
                        return null;
                    }
                }, null
        );
        return catNodesResponse;
    }

    public List<Node> nodeattrs() {
        return getLowLevelClient().getNodes();
    }
}
