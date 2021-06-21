package com.jn.esmvc.service.tcp.config;

import com.jn.esmvc.service.config.AbstractClientProperties;
import org.elasticsearch.plugins.Plugin;

import java.util.List;

public class EsmvcTransportClientProperties extends AbstractClientProperties {

    private List<Class<? extends Plugin>> plugins;

    public List<Class<? extends Plugin>> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<Class<? extends Plugin>> plugins) {
        this.plugins = plugins;
    }
    public EsmvcTransportClientProperties(){
        setProtocol("tcp");
    }
}
