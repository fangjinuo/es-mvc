package com.jn.esmvc.service.request;

import com.jn.esmvc.service.ClientProxy;
import com.jn.esmvc.service.ESRestClient;
import com.jn.esmvc.service.request.document.action.DelegatableActionListener;
import com.jn.esmvc.service.request.document.action.count.CountRequest;
import com.jn.esmvc.service.request.document.action.count.CountResponse;
import com.jn.esmvc.service.request.document.action.count.RestCountRequestAdapter;
import com.jn.esmvc.service.request.document.action.count.RestCountResponseAdapter;
import com.jn.esmvc.service.request.document.action.termvectors.RestTermVectorRequestAdapter;
import com.jn.esmvc.service.request.document.action.termvectors.RestTermVectorsResponseAdapter;
import com.jn.esmvc.service.request.document.action.termvectors.TermVectorsRequest;
import com.jn.esmvc.service.request.document.action.termvectors.TermVectorsResponse;
import com.jn.esmvc.service.request.indices.IndicesClientProxy;
import com.jn.esmvc.service.request.indices.rest.RestIndicesClientProxy;
import org.elasticsearch.action.ActionListener;
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
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class RestClientProxy extends ClientProxy<RestHighLevelClient, RequestOptions> {

    public RestClientProxy() {
        setGlobalOptions(RequestOptions.DEFAULT);
    }

    public RestClientProxy(ESRestClient restClient){
        this.set(restClient.getRestClient());
        this.setGlobalOptions(restClient.getRequestOptions());
    }

    public static RestClientProxy fromESRestClient(ESRestClient restClient) {
        return new RestClientProxy(restClient);
    }

    @Override
    public IndicesClientProxy indicesClient() {
        return new RestIndicesClientProxy(this);
    }

    private RequestOptions mergeRequestOptions(RequestOptions global, RequestOptions options) {
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
        return get().index(request, mergeRequestOptions(getGlobalOptions(), requestOptions));
    }

    @Override
    public void indexAsync(IndexRequest request, RequestOptions requestOptions, ActionListener<IndexResponse> listener) {
        get().indexAsync(request, mergeRequestOptions(getGlobalOptions(), requestOptions), listener);
    }

    @Override
    public DeleteResponse delete(DeleteRequest request, RequestOptions requestOptions) throws IOException {
        return get().delete(request, mergeRequestOptions(getGlobalOptions(), requestOptions));
    }

    @Override
    public void deleteAsync(DeleteRequest request, RequestOptions requestOptions, ActionListener<DeleteResponse> listener) {
        get().deleteAsync(request, mergeRequestOptions(getGlobalOptions(), requestOptions), listener);
    }

    @Override
    public UpdateResponse update(UpdateRequest request, RequestOptions requestOptions) throws IOException {
        return get().update(request, mergeRequestOptions(getGlobalOptions(), requestOptions));
    }

    @Override
    public void updateAsync(UpdateRequest request, RequestOptions requestOptions, ActionListener<UpdateResponse> listener) {
        get().updateAsync(request, mergeRequestOptions(getGlobalOptions(), requestOptions), listener);
    }

    @Override
    public GetResponse get(GetRequest request, RequestOptions options) throws IOException {
        return get().get(request, mergeRequestOptions(getGlobalOptions(), options));
    }

    @Override
    public void getAsync(GetRequest request, RequestOptions options, ActionListener<GetResponse> listener) {
        get().getAsync(request, mergeRequestOptions(getGlobalOptions(), options), listener);
    }

    @Override
    public MultiGetResponse mget(MultiGetRequest request, RequestOptions options) throws IOException {
        return get().mget(request, mergeRequestOptions(getGlobalOptions(), options));
    }

    @Override
    public void mgetAsync(MultiGetRequest request, RequestOptions options, ActionListener<MultiGetResponse> listener) {
        get().mgetAsync(request, mergeRequestOptions(getGlobalOptions(), options), listener);
    }

    @Override
    public BulkResponse bulk(BulkRequest request, RequestOptions options) throws IOException {
        return get().bulk(request, mergeRequestOptions(getGlobalOptions(), options));
    }

    @Override
    public void bulkAsync(BulkRequest request, RequestOptions requestOptions, ActionListener<BulkResponse> listener) {
        get().bulkAsync(request, mergeRequestOptions(getGlobalOptions(), requestOptions), listener);
    }

    @Override
    public SearchResponse search(SearchRequest request, RequestOptions options) throws IOException {
        return get().search(request, mergeRequestOptions(getGlobalOptions(), options));
    }

    @Override
    public void searchAsync(SearchRequest request, RequestOptions options, ActionListener<SearchResponse> listener) {
        get().searchAsync(request, mergeRequestOptions(getGlobalOptions(), options), listener);
    }

    @Override
    public SearchResponse searchScroll(SearchScrollRequest request, RequestOptions options) throws IOException {
        return get().scroll(request, mergeRequestOptions(getGlobalOptions(), options));
    }

    @Override
    public void searchScrollAsync(SearchScrollRequest request, RequestOptions options, ActionListener<SearchResponse> listener) {
        get().scrollAsync(request, mergeRequestOptions(getGlobalOptions(), options), listener);
    }

    @Override
    public ClearScrollResponse clearScroll(ClearScrollRequest request, RequestOptions options) throws IOException {
        return get().clearScroll(request, mergeRequestOptions(getGlobalOptions(), options));
    }

    @Override
    public void clearScrollAsync(ClearScrollRequest request, RequestOptions options, ActionListener<ClearScrollResponse> listener) {
        get().clearScrollAsync(request, mergeRequestOptions(getGlobalOptions(), options), listener);
    }

    @Override
    public MultiSearchResponse msearch(MultiSearchRequest request, RequestOptions options) throws IOException {
        return get().msearch(request, mergeRequestOptions(getGlobalOptions(), options));
    }

    @Override
    public void msearch(MultiSearchRequest request, RequestOptions options, ActionListener<MultiSearchResponse> listener) {
        get().msearchAsync(request, mergeRequestOptions(getGlobalOptions(), options), listener);
    }

    @Override
    public TermVectorsResponse termvectors(TermVectorsRequest request, RequestOptions options) throws IOException {
        RestTermVectorRequestAdapter requestAdapter = new RestTermVectorRequestAdapter();
        RestTermVectorsResponseAdapter responseAdapter = new RestTermVectorsResponseAdapter();
        return responseAdapter.apply(get().termvectors(requestAdapter.apply(request), mergeRequestOptions(getGlobalOptions(), options)));
    }

    @Override
    public void termvectorsAsync(TermVectorsRequest request, RequestOptions options, ActionListener<TermVectorsResponse> listener) {
        RestTermVectorRequestAdapter requestAdapter = new RestTermVectorRequestAdapter();
        RestTermVectorsResponseAdapter responseAdapter = new RestTermVectorsResponseAdapter();
        get().termvectorsAsync(requestAdapter.apply(request), options, new DelegatableActionListener<>(listener, responseAdapter));
    }

    @Override
    public CountResponse count(CountRequest request, RequestOptions requestOptions) throws IOException {
        RestCountRequestAdapter requestAdapter = new RestCountRequestAdapter();
        RestCountResponseAdapter responseAdapter = new RestCountResponseAdapter();
        return responseAdapter.apply(get().count(requestAdapter.apply(request), mergeRequestOptions(getGlobalOptions(), requestOptions)));
    }

    @Override
    public void countAsync(CountRequest request, RequestOptions requestOptions, ActionListener<CountResponse> listener) {
        RestCountRequestAdapter requestAdapter = new RestCountRequestAdapter();
        RestCountResponseAdapter responseAdapter = new RestCountResponseAdapter();
        get().countAsync(requestAdapter.apply(request), mergeRequestOptions(getGlobalOptions(), requestOptions), new DelegatableActionListener<>(listener, responseAdapter));
    }
}
