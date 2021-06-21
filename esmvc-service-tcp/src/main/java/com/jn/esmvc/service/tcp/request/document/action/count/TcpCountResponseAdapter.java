package com.jn.esmvc.service.tcp.request.document.action.count;

import com.jn.esmvc.service.request.document.action.ResponseAdapter;
import com.jn.esmvc.service.request.document.action.count.CountResponse;
import com.jn.esmvc.service.util.ESRequests;
import org.elasticsearch.action.search.SearchResponse;

public class TcpCountResponseAdapter implements ResponseAdapter<SearchResponse, CountResponse> {
    @Override
    public CountResponse apply(SearchResponse from) {
        CountResponse.ShardStats shardStats = new CountResponse.ShardStats(from.getSuccessfulShards(), from.getTotalShards(), from.getSkippedShards(), from.getShardFailures());
        return new CountResponse(ESRequests.getSearchTotalHits(from.getHits()), from.isTerminatedEarly(), shardStats);
    }
}
