package com.jn.esmvc.service;

import com.jn.esmvc.model.utils.ESClusterRestAddressParser;
import com.jn.esmvc.service.config.tcp.EsmvcTransportClientProperties;
import com.jn.langx.Builder;
import com.jn.langx.io.resource.ClassPathResource;
import com.jn.langx.io.resource.FileResource;
import com.jn.langx.io.resource.Resources;
import com.jn.langx.util.Emptys;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Consumer2;
import com.jn.langx.util.function.Function;
import com.jn.langx.util.net.NetworkAddress;
import com.jn.langx.util.reflect.type.Primitives;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;

public class ESTcpClientBuilder implements Builder<TransportClient> {
    private EsmvcTransportClientProperties esmvcTransportClientProperties;

    public ESTcpClientBuilder properties(EsmvcTransportClientProperties transportClientProperties) {
        this.esmvcTransportClientProperties = transportClientProperties;
        return this;
    }

    public TransportClient build() {
        if (Emptys.isEmpty(esmvcTransportClientProperties.getName())) {
            esmvcTransportClientProperties.setName("tcp-primary");
        }
        if (Emptys.isEmpty(esmvcTransportClientProperties.getProtocol())) {
            esmvcTransportClientProperties.setProtocol("tcp");
        }

        Settings.Builder builder = Settings.builder();
        if (Emptys.isNotEmpty(esmvcTransportClientProperties.getProps())) {
            Collects.forEach(esmvcTransportClientProperties.getProps(), new Consumer2<String, Object>() {
                @Override
                public void accept(String key, Object value) {
                    if (value == null) {
                        builder.putNull(key);
                        return;
                    }
                    if (value instanceof Number) {
                        if (Primitives.isInteger(value.getClass())) {
                            builder.put(key, (Integer) value);
                        } else if (Primitives.isDouble(value.getClass())) {
                            builder.put(key, (Double) value);
                        }
                        if (Primitives.isLong(value.getClass())) {
                            builder.put(key, (Long) value);
                        } else if (Primitives.isFloat(value.getClass())) {
                            builder.put(key, (Float) value);
                        }
                        return;
                    }
                    if (value instanceof Boolean) {
                        builder.put(key, (Boolean) value);
                    }
                    if (value instanceof String) {
                        if (((String) value).startsWith("classpath:")) {
                            ClassPathResource classPathResource = Resources.loadClassPathResource((String) value);
                            builder.put(key, classPathResource.getAbsolutePath());
                        } else if (((String) value).startsWith("file:")) {
                            FileResource fileResource = Resources.loadFileResource((File) value);
                            builder.put(key, fileResource.getAbsolutePath());
                        } else {
                            builder.put(key, (String) value);
                        }
                    }
                }
            });
        }
        Settings settings = builder.build();

        List<NetworkAddress> clusterAddress = new ESClusterRestAddressParser().parse(esmvcTransportClientProperties.getNodes());
        if (Emptys.isEmpty(clusterAddress)) {
            clusterAddress = Collects.newArrayList(new NetworkAddress("localhost", 9200));
        }
        TransportAddress[] addresses = Pipeline.of(clusterAddress).map(new Function<NetworkAddress, TransportAddress>() {
            @Override
            public TransportAddress apply(NetworkAddress networkAddress) {
                return new TransportAddress(new InetSocketAddress(networkAddress.getHost(), networkAddress.getPort()));
            }
        }).toArray(TransportAddress[].class);
        if (Emptys.isEmpty(esmvcTransportClientProperties.getPlugins())) {
            return new PreBuiltTransportClient(settings).addTransportAddresses(addresses);
        } else {
            List<Class<? extends Plugin>> plugins = esmvcTransportClientProperties.getPlugins();
            return new PreBuiltTransportClient(settings, plugins).addTransportAddresses(addresses);
        }

    }
}
