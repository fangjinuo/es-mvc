package com.jn.esmvc.service.scroll;

import com.jn.esmvc.service.ClientProxy;
import com.jn.esmvc.service.util.ESRequests;
import com.jn.langx.cache.*;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ScrollContextCache {
    private static final Logger logger = LoggerFactory.getLogger(ScrollContextCache.class);
    private List<ScrollContextCacheListener> listeners = new ArrayList<>();
    private ClientProxy clientProxy;
    private Cache<QueryBuilder, ScrollContext> localCache;

    private long expireInSeconds = 60;
    private int maxCapacity = Integer.MAX_VALUE;

    public void init() {
        localCache = CacheBuilder.<QueryBuilder, ScrollContext>newBuilder()
                .maxCapacity(maxCapacity)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .expireAfterWrite(expireInSeconds)
                .initialCapacity(100)
                .removeListener(new InternelGuavaRemoveListener())
                .loader(new Loader<QueryBuilder, ScrollContext>() {
                    @Override
                    public ScrollContext load(QueryBuilder key) {
                        for (ScrollContextCacheListener listener : listeners) {
                            ScrollContext value = listener.onLoad(key);
                            if (value != null) {
                                return value;
                            }
                        }
                        return null;
                    }


                    @Override
                    public Map<QueryBuilder, ScrollContext> getAll(Iterable<QueryBuilder> keys) {
                        return null;
                    }
                }).build();
    }

    public void addListener(ScrollContextCacheListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ScrollContextCacheListener listener) {
        listeners.remove(listener);
    }

    public void put(QueryBuilder key, ScrollContext value) {
        localCache.set(key, value, expireInSeconds, TimeUnit.SECONDS);
        for (ScrollContextCacheListener listener : listeners) {
            listener.onPut(key, value);
        }
    }

    public void clear(QueryBuilder key) {
        localCache.remove(key);
    }

    public ScrollContext get(QueryBuilder key) {
        try {
            return localCache.get(key);
        } catch (Throwable ex) {
            // NOOP
        }
        return null;
    }

    public long getExpireInSeconds() {
        return expireInSeconds;
    }

    public void setExpireInSeconds(long expireInSeconds) {
        this.expireInSeconds = expireInSeconds;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public ClientProxy getClientProxy() {
        return clientProxy;
    }

    public void setClientProxy(ClientProxy clientProxy) {
        this.clientProxy = clientProxy;
    }

    private class InternelGuavaRemoveListener implements RemoveListener<QueryBuilder, ScrollContext> {
        @Override
        public void onRemove(QueryBuilder key, ScrollContext value, RemoveCause cause) {
            try {
                for (ScrollContextCacheListener listener : listeners) {
                    listener.onRemove(key, value);
                }
            } catch (Throwable ex) {
                logger.error(ex.getMessage(), ex);
            } finally {
                try {
                    ESRequests.clearScroll(value.getScrollId(), clientProxy);
                } catch (Throwable ex) {
                    // IGNORE IT
                }
            }

        }
    }
}
