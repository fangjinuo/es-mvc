/*
 *  Copyright (c) 2016
 *  BES Software Corporation.
 *  All Rights Reserved
 *      Revision History:
 *                                 Modification       Tracking
 *  Author (Email ID)              Date               Number               Description
 *  -------------------------------------------------------------------------------------------
 *  jinuo.fang                     2019-04-18                              Initial version
 *
 */

package com.jn.esmvc.service.scroll;

import org.elasticsearch.index.query.QueryBuilder;

public interface ScrollContextCacheListener {
    void onPut(QueryBuilder key, ScrollContext value);

    void onRemove(QueryBuilder key, ScrollContext value);

    ScrollContext onLoad(QueryBuilder key);
}
