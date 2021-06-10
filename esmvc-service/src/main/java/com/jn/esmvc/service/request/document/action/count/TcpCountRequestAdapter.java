package com.jn.esmvc.service.request.document.action.count;

import com.jn.esmvc.service.request.document.action.RequestAdapter;
import com.jn.langx.util.Preconditions;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class TcpCountRequestAdapter implements RequestAdapter<CountRequest, SearchRequest> {
    @Override
    public SearchRequest apply(CountRequest from) {
        Preconditions.checkNotNull(from.countColumn());
        AggregationBuilder countBuilder = AggregationBuilders.count(from.countColumn());
        SearchSourceBuilder sourceBuilder = from.source();
        sourceBuilder.aggregation(countBuilder);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(sourceBuilder);
        searchRequest.indices(from.indices());
        searchRequest.types(from.types());
        searchRequest.allowPartialSearchResults(false);
        searchRequest.indicesOptions(from.indicesOptions());
        searchRequest.routing(from.routing());
        searchRequest.preference(from.preference());
        return searchRequest;
    }
}
