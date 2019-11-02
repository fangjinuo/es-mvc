package com.jn.esmvc.service.scroll;

import org.elasticsearch.index.query.QueryBuilder;

public interface ScrollContextCacheListener {
    void onPut(QueryBuilder key, ScrollContext value);

    void onRemove(QueryBuilder key, ScrollContext value);

    ScrollContext onLoad(QueryBuilder key);
}
