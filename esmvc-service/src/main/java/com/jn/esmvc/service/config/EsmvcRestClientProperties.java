package com.jn.esmvc.service.config;

import com.jn.langx.util.collection.Collects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EsmvcRestClientProperties extends AbstractClientProperties {
    public static final List<String> supportedProtocols = Collects.newArrayList("http", "https");
    private static final Logger logger = LoggerFactory.getLogger(EsmvcRestClientProperties.class);

    public EsmvcRestClientProperties(){
        setProtocol("http");
    }

    public void setProtocol(String protocol) {
        if (!supportedProtocols.contains(protocol)) {
            logger.warn("Unsupported protocol {} for esmvc", protocol);
            return;
        }
        super.setProtocol(protocol);
    }

}
