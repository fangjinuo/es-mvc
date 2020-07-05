package com.jn.esmvc.spring.boot;

import com.jn.esmvc.model.utils.ESClusterRestAddressParser;
import com.jn.esmvc.service.ESRestClient;
import com.jn.esmvc.service.config.EsmvcProperties;
import com.jn.esmvc.service.scroll.ScrollContextCache;
import com.jn.langx.util.Emptys;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Function;
import com.jn.langx.util.net.NetworkAddress;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConditionalOnMissingBean
@Configuration
public class EsmvcAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public EsmvcProperties esmvcProperties() {
        return new EsmvcProperties();
    }

    @Bean
    @Autowired
    @ConditionalOnExpression("#esmvcProperties.protocol=='http' || #esmvcProperties.protocol=='https'")
    public ESRestClient esRestClient(EsmvcProperties esmvcProperties) {
        List<NetworkAddress> clusterAddress = new ESClusterRestAddressParser().parse(esmvcProperties.getNodes());
        if (Emptys.isEmpty(clusterAddress)) {
            clusterAddress = Collects.newArrayList(new NetworkAddress("localhost", 9200));
        }
        String protocol = esmvcProperties.getProtocol();
        HttpHost[] restHosts = Pipeline.of(clusterAddress).map(new Function<NetworkAddress, HttpHost>() {
            @Override
            public HttpHost apply(NetworkAddress networkAddress) {
                return new HttpHost(networkAddress.getHost(), networkAddress.getPort());
            }
        }).toArray(HttpHost[].class);

        return new ESRestClient(new RestHighLevelClient(RestClient.builder(restHosts)));
    }

    @Bean
    @Autowired
    public ScrollContextCache scrollContextCache(EsmvcProperties esmvcProperties, ESRestClient esRestClient) {
        ScrollContextCache cache = new ScrollContextCache();
        cache.setExpireInSeconds(esmvcProperties.getLocalCacheExpireInSeconds());
        cache.setMaxCapacity(esmvcProperties.getLocalCacheMaxCapacity());
        cache.init();
        cache.setEsRestClient(esRestClient);
        return cache;
    }

}
