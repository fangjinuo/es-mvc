package com.jn.esmvc.service.config;

public class ScrollCacheProperties {
    private int localCacheMaxCapacity = 100;
    private int localCacheExpireInSeconds = 55;

    public int getLocalCacheMaxCapacity() {
        return localCacheMaxCapacity;
    }

    public void setLocalCacheMaxCapacity(int localCacheMaxCapacity) {
        this.localCacheMaxCapacity = localCacheMaxCapacity;
    }

    public int getLocalCacheExpireInSeconds() {
        return localCacheExpireInSeconds;
    }

    public void setLocalCacheExpireInSeconds(int localCacheExpireInSeconds) {
        this.localCacheExpireInSeconds = localCacheExpireInSeconds;
    }
}
