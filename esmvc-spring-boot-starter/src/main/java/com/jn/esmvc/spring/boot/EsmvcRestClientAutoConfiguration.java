package com.jn.esmvc.spring.boot;

import com.jn.esmvc.model.utils.ESClusterRestAddressParser;
import com.jn.esmvc.service.ESRestClient;
import com.jn.esmvc.service.config.EsmvcRestClientProperties;
import com.jn.langx.util.Emptys;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Function;
import com.jn.langx.util.net.NetworkAddress;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
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

import java.util.List;

@AutoConfigureAfter(ScrollContextCacheAutoConfiguration.class)
@ConditionalOnProperty(name = "esmvc.rest.enabled", havingValue = "true", matchIfMissing = true)
@ConditionalOnMissingBean(name = "esmvcRestClientAutoConfiguration")
@ConditionalOnClass(RestHighLevelClient.class)
@Configuration("esmvcRestClientAutoConfiguration")
public class EsmvcRestClientAutoConfiguration {

    @Bean("esmvcRestClientProperties")
    @ConditionalOnMissingBean(name = "esmvcRestClientProperties")
    @ConfigurationProperties(prefix = "esmvc.rest.primary")
    public EsmvcRestClientProperties esmvcRestClientProperties() {
        return new EsmvcRestClientProperties();
    }

    @Bean("esRestClient")
    @Primary
    @Autowired
    public ESRestClient esRestClient(@Qualifier("esmvcRestClientProperties") EsmvcRestClientProperties esmvcProperties) {
        if (Emptys.isEmpty(esmvcProperties.getProtocol())) {
            esmvcProperties.setProtocol("http");
        }
        if (Emptys.isEmpty(esmvcProperties.getName())) {
            esmvcProperties.setName("http-primary");
        }
        List<NetworkAddress> clusterAddress = new ESClusterRestAddressParser().parse(esmvcProperties.getNodes());
        if (Emptys.isEmpty(clusterAddress)) {
            clusterAddress = Collects.newArrayList(new NetworkAddress("localhost", 9200));
        }

        HttpHost[] restHosts = Pipeline.of(clusterAddress).map(new Function<NetworkAddress, HttpHost>() {
            @Override
            public HttpHost apply(NetworkAddress networkAddress) {
                return new HttpHost(networkAddress.getHost(), networkAddress.getPort());
            }
        }).toArray(HttpHost[].class);

        return new ESRestClient(new RestHighLevelClient(RestClient.builder(restHosts)));
    }


}
