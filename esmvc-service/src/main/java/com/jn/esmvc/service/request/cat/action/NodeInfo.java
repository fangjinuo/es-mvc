package com.jn.esmvc.service.request.cat.action;

import com.jn.langx.util.Objs;
import com.jn.langx.util.collection.Collects;

import java.util.Map;

public final class NodeInfo {
    /**
     * short id, 不是 full id
     */
    private String id;
    private String name;
    private String ip;
    private String host;
    private int port = -1;
    private int processId = -1;
    private Map<String, String> attrs = Collects.emptyHashMap();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NodeInfo)) {
            return false;
        }

        NodeInfo o = (NodeInfo) obj;
        return Objs.equals(this.id, this.name) || Objs.equals(this.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objs.hash(id, name, ip, host, port, processId);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    public void addAttr(String attr, String value) {
        this.attrs.put(attr, value);
    }
}
