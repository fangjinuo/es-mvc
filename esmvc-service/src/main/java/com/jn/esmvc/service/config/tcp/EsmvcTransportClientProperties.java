package com.jn.esmvc.service.config.tcp;

import com.jn.esmvc.service.config.AbstractClientProperties;

public class EsmvcTransportClientProperties extends AbstractClientProperties {
    public EsmvcTransportClientProperties(){
        setProtocol("tcp");
    }
}
