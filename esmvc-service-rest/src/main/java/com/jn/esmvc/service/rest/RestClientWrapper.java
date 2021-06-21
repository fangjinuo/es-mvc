package com.jn.esmvc.service.rest;

import com.jn.esmvc.service.ClientWrapper;
import com.jn.esmvc.service.request.cat.CatClientWrapper;
import com.jn.esmvc.service.request.document.action.DelegatableActionListener;
import com.jn.esmvc.service.request.document.action.count.CountRequest;
import com.jn.esmvc.service.request.document.action.count.CountResponse;
import com.jn.esmvc.service.rest.request.cat.RestCatClientWrapper;
import com.jn.esmvc.service.rest.request.document.action.count.RestCountRequestAdapter;
import com.jn.esmvc.service.rest.request.document.action.count.RestCountResponseAdapter;
import com.jn.esmvc.service.rest.request.document.action.termvectors.RestTermVectorRequestAdapter;
import com.jn.esmvc.service.rest.request.document.action.termvectors.RestTermVectorsResponseAdapter;
import com.jn.esmvc.service.request.document.action.termvectors.TermVectorsRequest;
import com.jn.esmvc.service.request.document.action.termvectors.TermVectorsResponse;
import com.jn.esmvc.service.request.indices.IndicesClientWrapper;
import com.jn.esmvc.service.rest.request.indices.RestIndicesClientWrapper;
import com.jn.langx.util.reflect.Reflects;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.CheckedFunction;
import org.elasticsearch.common.xcontent.XContentParser;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

public class RestClientWrapper extends ClientWrapper<RestHighLevelClient, RequestOptions> {

    public RestClientWrapper() {
        setGlobalOptions(RequestOptions.DEFAULT);
    }

    public RestClientWrapper(ESRestClient restClient) {
        this.set(restClient.getRestClient());
        this.setGlobalOptions(restClient.getRequestOptions());
    }

    public static RestClientWrapper fromESRestClient(ESRestClient restClient) {
        return new RestClientWrapper(restClient);
    }

    @Override
    public RestIndicesClientWrapper indicesClient() {
        return new RestIndicesClientWrapper(this);
    }

    @Override
    public RestCatClientWrapper cat() {
        return new RestCatClientWrapper(this);
    }

    public RequestOptions mergeRequestOptions(RequestOptions options) {
        RequestOptions global = getGlobalOptions();
        if (global == null && options == null) {
            setGlobalOptions(RequestOptions.DEFAULT);
        }
        if (options == null) {
            return global;
        }
        return options;
    }

    @Override
    public IndexResponse index(IndexRequest request, RequestOptions requestOptions) throws IOException {
        return get().index(request, mergeRequestOptions(requestOptions));
    }

    @Override
    public void indexAsync(IndexRequest request, RequestOptions requestOptions, ActionListener<IndexResponse> listener) {
        get().indexAsync(request, mergeRequestOptions(requestOptions), listener);
    }

    @Override
    public DeleteResponse delete(DeleteRequest request, RequestOptions requestOptions) throws IOException {
        return get().delete(request, mergeRequestOptions(requestOptions));
    }

    @Override
    public void deleteAsync(DeleteRequest request, RequestOptions requestOptions, ActionListener<DeleteResponse> listener) {
        get().deleteAsync(request, mergeRequestOptions(requestOptions), listener);
    }

    @Override
    public UpdateResponse update(UpdateRequest request, RequestOptions requestOptions) throws IOException {
        return get().update(request, mergeRequestOptions(requestOptions));
    }

    @Override
    public void updateAsync(UpdateRequest request, RequestOptions requestOptions, ActionListener<UpdateResponse> listener) {
        get().updateAsync(request, mergeRequestOptions(requestOptions), listener);
    }

    @Override
    public GetResponse get(GetRequest request, RequestOptions options) throws IOException {
        return get().get(request, mergeRequestOptions(options));
    }

    @Override
    public void getAsync(GetRequest request, RequestOptions options, ActionListener<GetResponse> listener) {
        get().getAsync(request, mergeRequestOptions(options), listener);
    }

    @Override
    public MultiGetResponse mget(MultiGetRequest request, RequestOptions options) throws IOException {
        return get().mget(request, mergeRequestOptions(options));
    }

    @Override
    public void mgetAsync(MultiGetRequest request, RequestOptions options, ActionListener<MultiGetResponse> listener) {
        get().mgetAsync(request, mergeRequestOptions(options), listener);
    }

    @Override
    public BulkResponse bulk(BulkRequest request, RequestOptions options) throws IOException {
        return get().bulk(request, mergeRequestOptions(options));
    }

    @Override
    public void bulkAsync(BulkRequest request, RequestOptions requestOptions, ActionListener<BulkResponse> listener) {
        get().bulkAsync(request, mergeRequestOptions(requestOptions), listener);
    }

    @Override
    public SearchResponse search(SearchRequest request, RequestOptions options) throws IOException {
        return get().search(request, mergeRequestOptions(options));
    }

    @Override
    public void searchAsync(SearchRequest request, RequestOptions options, ActionListener<SearchResponse> listener) {
        get().searchAsync(request, mergeRequestOptions(options), listener);
    }

    @Override
    public SearchResponse searchScroll(SearchScrollRequest request, RequestOptions options) throws IOException {
        return get().scroll(request, mergeRequestOptions(options));
    }

    @Override
    public void searchScrollAsync(SearchScrollRequest request, RequestOptions options, ActionListener<SearchResponse> listener) {
        get().scrollAsync(request, mergeRequestOptions(options), listener);
    }

    @Override
    public ClearScrollResponse clearScroll(ClearScrollRequest request, RequestOptions options) throws IOException {
        return get().clearScroll(request, mergeRequestOptions(options));
    }

    @Override
    public void clearScrollAsync(ClearScrollRequest request, RequestOptions options, ActionListener<ClearScrollResponse> listener) {
        get().clearScrollAsync(request, mergeRequestOptions(options), listener);
    }

    @Override
    public MultiSearchResponse msearch(MultiSearchRequest request, RequestOptions options) throws IOException {
        return get().msearch(request, mergeRequestOptions(options));
    }

    @Override
    public void msearch(MultiSearchRequest request, RequestOptions options, ActionListener<MultiSearchResponse> listener) {
        get().msearchAsync(request, mergeRequestOptions(options), listener);
    }

    @Override
    public TermVectorsResponse termVectors(TermVectorsRequest request, RequestOptions options) throws IOException {
        RestTermVectorRequestAdapter requestAdapter = new RestTermVectorRequestAdapter();
        RestTermVectorsResponseAdapter responseAdapter = new RestTermVectorsResponseAdapter();
        return responseAdapter.apply(get().termvectors(requestAdapter.apply(request), mergeRequestOptions(options)));
    }

    @Override
    public void termVectorsAsync(TermVectorsRequest request, RequestOptions options, ActionListener<TermVectorsResponse> listener) {
        RestTermVectorRequestAdapter requestAdapter = new RestTermVectorRequestAdapter();
        RestTermVectorsResponseAdapter responseAdapter = new RestTermVectorsResponseAdapter();
        get().termvectorsAsync(requestAdapter.apply(request), options, new DelegatableActionListener<>(listener, responseAdapter));
    }

    @Override
    public CountResponse count(CountRequest request, RequestOptions requestOptions) throws IOException {
        RestCountRequestAdapter requestAdapter = new RestCountRequestAdapter();
        RestCountResponseAdapter responseAdapter = new RestCountResponseAdapter();
        return responseAdapter.apply(get().count(requestAdapter.apply(request), mergeRequestOptions(requestOptions)));
    }

    @Override
    public void countAsync(CountRequest request, RequestOptions requestOptions, ActionListener<CountResponse> listener) {
        RestCountRequestAdapter requestAdapter = new RestCountRequestAdapter();
        RestCountResponseAdapter responseAdapter = new RestCountResponseAdapter();
        get().countAsync(requestAdapter.apply(request), mergeRequestOptions(requestOptions), new DelegatableActionListener<>(listener, responseAdapter));
    }

    private static final Method restHighLevelClient_performRequestAndParseEntity = Reflects.getDeclaredMethod(
            RestHighLevelClient.class, "performRequestAndParseEntity",
            ActionRequest.class,
            CheckedFunction.class,
            RequestOptions.class,
            CheckedFunction.class,
            Set.class);

    public final <Req extends ActionRequest, Resp> Resp performRequestAndParseEntity(Req request,
                                                                                     CheckedFunction<Req, Request, IOException> requestConverter,
                                                                                     RequestOptions options,
                                                                                     CheckedFunction<XContentParser, Resp, IOException> entityParser,
                                                                                     Set<Integer> ignores) {
        return Reflects.invoke(restHighLevelClient_performRequestAndParseEntity,
                get(),
                new Object[]{request, requestConverter, options, entityParser, ignores},
                true, true);
    }

}
