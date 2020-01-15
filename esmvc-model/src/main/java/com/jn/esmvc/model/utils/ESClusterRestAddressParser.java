package com.jn.esmvc.model.utils;

import com.jn.langx.util.Strings;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.function.Consumer;
import com.jn.langx.util.net.NetworkAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ESClusterRestAddressParser {
    private static final Logger logger = LoggerFactory.getLogger(ESClusterRestAddressParser.class);

    private static final int DEFAULT_PORT = 9200;

    private static final String delimiters = ",:";

    /**
     * <pre>
     * case 1:
     *  host1:port1,host2:port2,host3,host4:port4 => host1:port1, host2:port2,host3:defaultPort,host4:port4
     * case 2:
     *  host1,host2,host3:port  => host1:port,host2:port,host3:port
     * </pre>
     */
    public List<NetworkAddress> parse(String s) {
        if (Strings.isBlank(s)) {
            return Collections.emptyList();
        }
        boolean useUnifiedPort = true;
        int firstPort = -1;
        StringTokenizer tokenizer = new StringTokenizer(s, delimiters, true);

        NetworkAddress lastNetworkAddress = null;

        List<NetworkAddress> ret = new LinkedList<NetworkAddress>();

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                if (delimiters.contains(token)) {
                    if (":".equals(token) && tokenizer.hasMoreTokens()) {
                        if (lastNetworkAddress != null) {
                            token = tokenizer.nextToken();

                            try {
                                int port = Integer.parseInt(token);
                                lastNetworkAddress.setPort(port);
                                if (firstPort == -1) {
                                    firstPort = port;
                                }
                                if (firstPort != -1 && firstPort != port) {
                                    useUnifiedPort = false;
                                }
                                lastNetworkAddress = null;
                            } catch (NumberFormatException ex) {
                                logger.warn("Parse ElasticSearch cluster restful's port error:", ex);
                            }
                        }
                    }
                } else {
                    lastNetworkAddress = new NetworkAddress();
                    lastNetworkAddress.setHost(token);
                    ret.add(lastNetworkAddress);
                }
            }
        }
        if (lastNetworkAddress != null) {
            int port = DEFAULT_PORT;
            lastNetworkAddress.setPort(port);
            if (firstPort == -1) {
                firstPort = port;
            }
            if (firstPort != port) {
                useUnifiedPort = false;
            }
        }
        if (useUnifiedPort) {
            final int unifiedPort = firstPort == -1 ? DEFAULT_PORT : firstPort;
            Collects.forEach(ret, new Consumer<NetworkAddress>() {
                @Override
                public void accept(NetworkAddress address) {
                    address.setPort(unifiedPort);
                }
            });
        } else {
            Collects.forEach(ret, new Consumer<NetworkAddress>() {
                @Override
                public void accept(NetworkAddress address) {
                    if (address.getPort() == 0) {
                        address.setPort(DEFAULT_PORT);
                    }
                }
            });
        }

        return ret;
    }


}
