package com.jn.esmvc.service;

import com.jn.esmvc.model.utils.ESClusterRestAddressParser;
import com.jn.esmvc.service.config.rest.EsmvcRestClientProperties;
import com.jn.esmvc.service.config.rest.RestClientBuilderCustomizer;
import com.jn.langx.Builder;
import com.jn.langx.util.Emptys;
import com.jn.langx.util.Strings;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Consumer;
import com.jn.langx.util.function.Function;
import com.jn.langx.util.net.NetworkAddress;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.List;

public class ESRestClientBuilder implements Builder<ESRestClient> {
    private EsmvcRestClientProperties esmvcProperties;
    public ESRestClientBuilder properties(EsmvcRestClientProperties properties){
        this.esmvcProperties = properties;
        return this;
    }
    private List<RestClientBuilderCustomizer> builderCustomizers;

    public ESRestClientBuilder restClientBuilderCustomizers(List<RestClientBuilderCustomizer> restClientBuilderCustomizers){
        this.builderCustomizers  = restClientBuilderCustomizers;
        return this;
    }

    public ESRestClient build(){
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
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                Collects.forEach(builderCustomizers, new Consumer<RestClientBuilderCustomizer>() {
                    @Override
                    public void accept(RestClientBuilderCustomizer restClientBuilderCustomizer) {
                        restClientBuilderCustomizer.customize(httpClientBuilder);
                    }
                });
                return httpClientBuilder;
            }
        });
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                Collects.forEach(builderCustomizers, new Consumer<RestClientBuilderCustomizer>() {
                    @Override
                    public void accept(RestClientBuilderCustomizer restClientBuilderCustomizer) {
                        restClientBuilderCustomizer.customize(requestConfigBuilder);
                    }
                });
                return requestConfigBuilder;
            }
        });
        Collects.forEach(builderCustomizers, new Consumer<RestClientBuilderCustomizer>() {
            @Override
            public void accept(RestClientBuilderCustomizer restClientBuilderCustomizer) {
                restClientBuilderCustomizer.customize(builder);
            }
        });
        return new ESRestClient(new RestHighLevelClient(builder));
    }
}
