/*
 *  Copyright (c) 2016
 *  BES Software Corporation.
 *  All Rights Reserved
 *      Revision History:
 *                                 Modification       Tracking
 *  Author (Email ID)              Date               Number               Description
 *  -------------------------------------------------------------------------------------------
 *  jinuo.fang                     2019-04-16                              Initial version
 *
 */

package com.jn.esmvc.service.util;

import com.jn.esmvc.service.ESRestClient;
import com.jn.langx.util.Preconditions;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESRequests {
    private static final Logger logger = LoggerFactory.getLogger(ESRequests.class);

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
}
