package com.jn.esmvc.service.config;

public abstract class AbstractClientProperties {
    private String name;
    private String protocol;
    private String nodes;
    private int localCacheMaxCapacity = 100;
    private int localCacheExpireInSeconds = 55;

    public String getProtocol() {
        return protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

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
