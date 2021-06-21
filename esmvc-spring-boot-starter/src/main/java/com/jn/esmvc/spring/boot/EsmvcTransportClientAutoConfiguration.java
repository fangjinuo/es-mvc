package com.jn.esmvc.spring.boot;

import com.jn.esmvc.service.tcp.ESTcpClientBuilder;
import com.jn.esmvc.service.config.tcp.EsmvcTransportClientProperties;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@AutoConfigureAfter(ScrollContextCacheAutoConfiguration.class)
@ConditionalOnProperty(name = "esmvc.tcp.enabled", havingValue = "true", matchIfMissing = false)
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
        return new ESTcpClientBuilder().properties(esmvcTransportClientProperties).build();
    }

}
