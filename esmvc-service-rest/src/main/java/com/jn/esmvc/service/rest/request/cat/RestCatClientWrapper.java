package com.jn.esmvc.service.rest.request.cat;

import com.jn.esmvc.service.request.cat.CatClientWrapper;
import com.jn.esmvc.service.request.cat.action.CatNodeAttrsResponse;
import com.jn.esmvc.service.request.cat.action.CatNodesRequest;
import com.jn.esmvc.service.request.cat.action.CatNodesResponse;
import com.jn.esmvc.service.rest.RestClientWrapper;
import com.jn.langx.http.HttpMethod;
import com.jn.langx.util.Objs;
import com.jn.langx.util.Strings;
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

    /**
     * GET /_cat/nodes
     *
     * @param requestOptions
     * @param request
     * @return
     */
    @Override
    public CatNodesResponse nodes(RequestOptions requestOptions, CatNodesRequest request) {
        RequestOptions options = restClientWrapper.mergeRequestOptions(requestOptions);

        CatNodesResponse catNodesResponse = getClientWrapper().performRequestAndParseEntity(request,
                new CheckedFunction<CatNodesRequest, Request, IOException>() {
                    @Override
                    public Request apply(CatNodesRequest request) throws IOException {
                        Request req = new Request(HttpMethod.GET.name(), "/_cat/nodes");

                        if (request.getDataUnit() != null) {
                            req.addParameter("bytes", request.getDataUnit().getStandardSymbol());
                        }
                        if (Strings.isNotEmpty(request.getFormat())) {
                            req.addParameter("format", request.getFormat());
                        }
                        req.addParameter("full_id", "true");
                        if (Objs.isNotEmpty(request.getMetrics())) {
                            req.addParameter("h", Strings.join(",", request.getMetrics()));
                        }
                        if (request.getTimeUnit() != null) {
                            req.addParameter("time", request.getTimeUnit().getSimpleName());
                        }
                        return req;
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

    public CatNodeAttrsResponse nodeattrs() {
        List<Node> nodes = getLowLevelClient().getNodes();
        return null;
    }
}
