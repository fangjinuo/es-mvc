package com.jn.esmvc.model.utils;

import com.jn.langx.util.net.ClusterAddressParser;

public class ESClusterRestAddressParser extends ClusterAddressParser {

    public ESClusterRestAddressParser() {
        super();
    }

    public ESClusterRestAddressParser(int defaultPort) {
        super(defaultPort);
    }
}
