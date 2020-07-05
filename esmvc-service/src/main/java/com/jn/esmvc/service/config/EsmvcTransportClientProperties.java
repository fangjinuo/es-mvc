package com.jn.esmvc.service.config;

import java.util.Map;

public class EsmvcTransportClientProperties extends AbstractClientProperties {
    private Map<String, Object> props;

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }
}
