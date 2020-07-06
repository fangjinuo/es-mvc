package com.jn.esmvc.service.util;

import com.jn.esmvc.service.ClientProxy;
import com.jn.esmvc.service.ESRestClient;
import com.jn.langx.util.Preconditions;
import com.jn.langx.util.pagination.PagingRequest;
import com.jn.langx.util.struct.ThreadLocalHolder;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESRequests {
    private static final Logger logger = LoggerFactory.getLogger(ESRequests.class);
    public static final ThreadLocalHolder<PagingRequest> ES_PAGING = new ThreadLocalHolder<PagingRequest>();
    public static void logRequestWhenFail(Logger logger, ActionRequest request, Throwable ex) {
        logger.error("Error occur when execute elasticsearch restful request {}", request, ex);
    }

    public static void clearScroll(String scrollId, ESRestClient esRestClient) {
        Preconditions.checkNotNull(scrollId);
        ClearScrollRequest request = new ClearScrollRequest();
        request.addScrollId(scrollId);
        try {
            esRestClient.getRestClient().clearScroll(request, esRestClient.getRequestOptions());
        } catch (Exception ex) {
            logRequestWhenFail(logger, request, ex);
        }
    }

    public static void clearScroll(String scrollId, ClientProxy clientProxy) {
        Preconditions.checkNotNull(scrollId);
        ClearScrollRequest request = new ClearScrollRequest();
        request.addScrollId(scrollId);
        try {
            clientProxy.clearScroll(request, null);
        } catch (Exception ex) {
            logRequestWhenFail(logger, request, ex);
        }
    }
}
