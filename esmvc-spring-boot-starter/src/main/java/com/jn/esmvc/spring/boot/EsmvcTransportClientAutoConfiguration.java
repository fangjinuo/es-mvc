package com.jn.esmvc.spring.boot;

import com.jn.esmvc.model.utils.ESClusterRestAddressParser;
import com.jn.esmvc.service.config.EsmvcTransportClientProperties;
import com.jn.langx.util.Emptys;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Consumer2;
import com.jn.langx.util.function.Function;
import com.jn.langx.util.net.NetworkAddress;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.InetSocketAddress;
import java.util.List;

@ConditionalOnProperty(name = "esmvc.tcp.enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnMissingBean(name = "esmvcTransportClientAutoConfiguration")
@ConditionalOnClass(PreBuiltTransportClient.class)
@Configuration("esmvcTransportClientAutoConfiguration")
public class EsmvcTransportClientAutoConfiguration {


    @ConditionalOnMissingBean(name = "esmvcTransportClientProperties")
    @Bean("esmvcTransportClientProperties")
    @ConfigurationProperties(prefix = "esmvc.tcp.primary")
    public EsmvcTransportClientProperties esmvcTransportClientProperties() {
        return new EsmvcTransportClientProperties();
    }

    @Bean("transportClient")
    @ConditionalOnMissingBean(name = "transportClient")
    @Primary
    @Autowired
    public TransportClient transportClient(@Qualifier("esmvcTransportClientProperties") EsmvcTransportClientProperties esmvcTransportClientProperties) {
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
                        if (value.getClass() == int.class || value.getClass() == Integer.class) {
                            builder.put(key, (Integer) value);
                        } else if (value.getClass() == double.class || value.getClass() == Double.class) {
                            builder.put(key, (Double) value);
                        }
                        if (value.getClass() == long.class || value.getClass() == Long.class) {
                            builder.put(key, (Long) value);
                        } else if (value.getClass() == float.class || value.getClass() == Float.class) {
                            builder.put(key, (Float) value);
                        }
                        return;
                    }
                    if (value instanceof Boolean) {
                        builder.put(key, (Boolean) value);
                    }
                    if (value instanceof String) {
                        builder.put(key, (String) value);
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

        return new PreBuiltTransportClient(settings).addTransportAddresses(addresses);
    }

}
