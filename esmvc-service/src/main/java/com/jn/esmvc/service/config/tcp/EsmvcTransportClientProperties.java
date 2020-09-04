package com.jn.esmvc.service.config.tcp;

import com.jn.esmvc.service.config.AbstractClientProperties;

import java.util.Map;

public class EsmvcTransportClientProperties extends AbstractClientProperties {
    private Map<String, Object> props;

    public EsmvcTransportClientProperties(){
        setProtocol("tcp");
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }
}
