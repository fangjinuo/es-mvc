package com.jn.esmvc.service.config;

import java.util.Map;

public abstract class AbstractClientProperties {
    private String name;
    private String protocol;
    private String nodes;

    private String username;
    private String password;

    private int connectTimeout = 5; // mills
    private int readTimeout = 120; // mills

    private Map<String, Object> props;

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        if (connectTimeout >= 0) {
            this.connectTimeout = connectTimeout;
        }
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        if (readTimeout >= 0) {
            this.readTimeout = readTimeout;
        }
    }

}
