package com.jn.esmvc.service.request.action.count;

import com.jn.esmvc.service.request.action.ResponseAdapter;
import org.elasticsearch.action.search.SearchResponse;

public class TcpCountResponseAdapter implements ResponseAdapter<SearchResponse, CountResponse> {
    @Override
    public CountResponse apply(SearchResponse from) {
        CountResponse.ShardStats shardStats = new CountResponse.ShardStats(from.getSuccessfulShards(), from.getTotalShards(), from.getSkippedShards(), from.getShardFailures());
        return new CountResponse(from.getHits().getTotalHits(), from.isTerminatedEarly(), shardStats);
    }
}
