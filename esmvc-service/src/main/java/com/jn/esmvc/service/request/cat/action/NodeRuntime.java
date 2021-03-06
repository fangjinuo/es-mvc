package com.jn.esmvc.service.request.cat.action;

import com.jn.langx.util.Strings;
import com.jn.langx.util.collection.StringMap;
import com.jn.langx.util.collection.StringMapAccessor;

import java.util.Map;

public class NodeRuntime {
    private String id;
    private String name;
    private String ip;
    private String nodeRole;
    private boolean master;
    private int pid;
    private int tcpPort;
    private int httpPort;
    private String httpAddress;
    private String version;
    private String build;
    private String jdk;

    private Map<String, String> metrics;

    public NodeRuntime() {
    }

    public NodeRuntime(Map<String, String> metrics) {
        this.metrics = metrics;
        StringMapAccessor accessor = new StringMapAccessor(new StringMap(metrics));

        setId(accessor.getString("nodeId"));
        this.metrics.remove("nodeId");

        setName(accessor.getString("name"));
        this.metrics.remove("name");

        setIp(accessor.getString("ip"));
        this.metrics.remove("ip");

        setNodeRole(accessor.getString("nodeRole"));
        this.metrics.remove("nodeRole");

        setMaster("*".equals(accessor.getString("master", "-")));
        this.metrics.remove("master");

        setPid(accessor.getInteger("pid"));
        this.metrics.remove("pid");

        setTcpPort(accessor.getInteger("port"));
        this.metrics.remove("port");

        setVersion(accessor.getString("version"));
        this.metrics.remove("version");

        setBuild(accessor.getString("build"));
        this.metrics.remove("build");

        setJdk(accessor.getString("jdk"));
        this.metrics.remove("jdk");

        setHttpAddress(accessor.getString("http_address"));
        this.metrics.remove("http_address");

        String httpAddress = getHttpAddress();
        if (httpAddress != null) {
            httpAddress = httpAddress.replace(getIp(),"");
            int httpPort = Integer.parseInt(Strings.split(httpAddress,":")[0]);
            setHttpPort(httpPort);
        }
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

    public String getNodeRole() {
        return nodeRole;
    }

    public void setNodeRole(String nodeRole) {
        this.nodeRole = nodeRole;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public String getHttpAddress() {
        return httpAddress;
    }

    public void setHttpAddress(String httpAddress) {
        this.httpAddress = httpAddress;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getJdk() {
        return jdk;
    }

    public void setJdk(String jdk) {
        this.jdk = jdk;
    }

    public Map<String, String> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, String> metrics) {
        this.metrics = metrics;
    }
}
