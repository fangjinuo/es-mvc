package com.jn.esmvc.service.request;

import com.jn.esmvc.service.ClientProxy;
import com.jn.esmvc.service.request.action.DelegatableActionListener;
import com.jn.esmvc.service.request.action.count.CountRequest;
import com.jn.esmvc.service.request.action.count.CountResponse;
import com.jn.esmvc.service.request.action.termvectors.TcpTermVectorsRequestAdapter;
import com.jn.esmvc.service.request.action.termvectors.TcpTermVectorsResponseAdapter;
import com.jn.esmvc.service.request.action.termvectors.TermVectorsRequest;
import com.jn.esmvc.service.request.action.termvectors.TermVectorsResponse;
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
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;

public class TcpClientProxy extends ClientProxy<TransportClient, Object> {

    public static TcpClientProxy fromTransportClient(TransportClient transportClient){
        TcpClientProxy proxy = new TcpClientProxy();
        proxy.set(transportClient);
        return proxy;
    }

    @Override
    public IndexResponse index(IndexRequest request, Object o) throws IOException {
        return get().index(request).actionGet();
    }

    @Override
    public void indexAsync(IndexRequest request, Object o, ActionListener<IndexResponse> listener) {
        get().index(request, listener);
    }

    @Override
    public DeleteResponse delete(DeleteRequest request, Object o) throws IOException {
        return get().delete(request).actionGet();
    }

    @Override
    public void deleteAsync(DeleteRequest request, Object o, ActionListener<DeleteResponse> listener) {
        get().delete(request, listener);
    }

    @Override
    public UpdateResponse update(UpdateRequest request, Object o) throws IOException {
        return get().update(request).actionGet();
    }

    @Override
    public void updateAsync(UpdateRequest request, Object o, ActionListener<UpdateResponse> listener) {
        get().update(request, listener);
    }

    @Override
    public GetResponse get(GetRequest request, Object options) throws IOException {
        return get().get(request).actionGet();
    }

    @Override
    public void getAsync(GetRequest request, Object options, ActionListener<GetResponse> listener) {
        get().get(request, listener);
    }

    @Override
    public MultiGetResponse mget(MultiGetRequest request, Object o) throws IOException {
        return get().multiGet(request).actionGet();
    }

    @Override
    public void mgetAsync(MultiGetRequest request, Object o, ActionListener<MultiGetResponse> listener) {
        get().multiGet(request, listener);
    }

    @Override
    public BulkResponse bulk(BulkRequest request, Object o) throws IOException {
        return get().bulk(request).actionGet();
    }

    @Override
    public void bulkAsync(BulkRequest request, Object o, ActionListener<BulkResponse> listener) {
        get().bulk(request, listener);
    }

    @Override
    public SearchResponse search(SearchRequest request, Object o) throws IOException {
        return get().search(request).actionGet();
    }

    @Override
    public void searchAsync(SearchRequest request, Object o, ActionListener<SearchResponse> listener) {
        get().search(request, listener);
    }

    @Override
    public SearchResponse searchScroll(SearchScrollRequest request, Object o) throws IOException {
        return get().searchScroll(request).actionGet();
    }

    @Override
    public void searchScrollAsync(SearchScrollRequest request, Object o, ActionListener<SearchResponse> listener) {
        get().searchScroll(request, listener);
    }

    @Override
    public ClearScrollResponse clearScroll(ClearScrollRequest request, Object o) throws IOException {
        return get().clearScroll(request).actionGet();
    }

    @Override
    public void clearScrollAsync(ClearScrollRequest request, Object o, ActionListener<ClearScrollResponse> listener) {
        get().clearScroll(request, listener);
    }

    @Override
    public MultiSearchResponse msearch(MultiSearchRequest request, Object o) throws IOException {
        return get().multiSearch(request).actionGet();
    }

    @Override
    public void msearch(MultiSearchRequest request, Object o, ActionListener<MultiSearchResponse> listener) {
        get().multiSearch(request, listener);
    }

    @Override
    public TermVectorsResponse termvectors(TermVectorsRequest request, Object o) throws IOException {
        TcpTermVectorsRequestAdapter requestAdapter = new TcpTermVectorsRequestAdapter();
        TcpTermVectorsResponseAdapter responseAdapter = new TcpTermVectorsResponseAdapter();
        return responseAdapter.apply(get().termVectors(requestAdapter.apply(request)).actionGet());
    }

    @Override
    public void termvectorsAsync(TermVectorsRequest request, Object o, ActionListener<TermVectorsResponse> listener) {
        TcpTermVectorsRequestAdapter requestAdapter = new TcpTermVectorsRequestAdapter();
        TcpTermVectorsResponseAdapter responseAdapter = new TcpTermVectorsResponseAdapter();
        get().termVectors(requestAdapter.apply(request), new DelegatableActionListener<>(listener, responseAdapter));
    }

    @Override
    public CountResponse count(CountRequest request, Object o) throws IOException {
        return null;
    }

    @Override
    public void countAsync(CountRequest request, Object o, ActionListener<CountResponse> listener) {

    }
}
