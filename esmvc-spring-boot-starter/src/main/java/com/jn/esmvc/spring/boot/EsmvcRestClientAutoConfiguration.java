package com.jn.esmvc.spring.boot;

import com.jn.esmvc.model.utils.ESClusterRestAddressParser;
import com.jn.esmvc.service.ESRestClient;
import com.jn.esmvc.service.config.rest.DefaultRestClientBuilderCustomizer;
import com.jn.esmvc.service.config.rest.EsmvcRestClientProperties;
import com.jn.esmvc.service.config.rest.RestClientBuilderCustomizer;
import com.jn.langx.util.Emptys;
import com.jn.langx.util.Strings;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Function;
import com.jn.langx.util.net.NetworkAddress;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
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

    @Bean("restClientBuilderCustomizer")
    @ConditionalOnMissingBean(name = "restClientBuilderCustomizer")
    public RestClientBuilderCustomizer restClientBuilderCustomizer(EsmvcRestClientProperties esmvcRestClientProperties){
        return new DefaultRestClientBuilderCustomizer(esmvcRestClientProperties);
    }

    @Bean("esRestClient")
    @Primary
    @Autowired
    public ESRestClient esRestClient(
            @Qualifier("esmvcRestClientProperties") EsmvcRestClientProperties esmvcProperties,
            final @Qualifier("restClientBuilderCustomizer")RestClientBuilderCustomizer restClientBuilderCustomizer) {
        esmvcProperties.setProtocol(Strings.useValueIfEmpty(esmvcProperties.getProtocol(), "http"));
        esmvcProperties.setName(Strings.useValueIfEmpty(esmvcProperties.getName(), "http-primary"));
        List<NetworkAddress> clusterAddress = new ESClusterRestAddressParser(9200).parse(esmvcProperties.getNodes());
        if (Emptys.isEmpty(clusterAddress)) {
            clusterAddress = Collects.newArrayList(new NetworkAddress("localhost", 9200));
        }

        HttpHost[] restHosts = Pipeline.of(clusterAddress).map(new Function<NetworkAddress, HttpHost>() {
            @Override
            public HttpHost apply(NetworkAddress networkAddress) {
                return new HttpHost(networkAddress.getHost(), networkAddress.getPort());
            }
        }).toArray(HttpHost[].class);

        RestClientBuilder builder = RestClient.builder(restHosts);
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback(){
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                restClientBuilderCustomizer.customize(httpClientBuilder);
                return httpClientBuilder;
            }
        });
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                restClientBuilderCustomizer.customize(requestConfigBuilder);
                return requestConfigBuilder;
            }
        });
        restClientBuilderCustomizer.customize(builder);
        return new ESRestClient(new RestHighLevelClient(builder));
    }


}
