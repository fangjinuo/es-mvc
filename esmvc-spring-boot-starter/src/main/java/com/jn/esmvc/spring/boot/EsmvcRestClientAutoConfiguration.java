package com.jn.esmvc.spring.boot;

import com.jn.esmvc.service.config.rest.DefaultRestClientBuilderCustomizer;
import com.jn.esmvc.service.config.rest.EsmvcRestClientProperties;
import com.jn.esmvc.service.config.rest.RestClientBuilderCustomizer;
import com.jn.esmvc.service.rest.ESRestClient;
import com.jn.esmvc.service.rest.ESRestClientBuilder;
import com.jn.langx.util.Emptys;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.ObjectProvider;
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
    public RestClientBuilderCustomizer restClientBuilderCustomizer(EsmvcRestClientProperties esmvcRestClientProperties) {
        return new DefaultRestClientBuilderCustomizer(esmvcRestClientProperties);
    }

    @Bean("esRestClient")
    @Primary
    @Autowired
    public ESRestClient esRestClient(
            @Qualifier("esmvcRestClientProperties") EsmvcRestClientProperties esmvcProperties,
            ObjectProvider<List<RestClientBuilderCustomizer>> builderCustomizers) {
        ESRestClientBuilder builder = new ESRestClientBuilder().properties(esmvcProperties);
        List<RestClientBuilderCustomizer> customizers = builderCustomizers.getIfAvailable();
        if(Emptys.isNotEmpty(customizers)){
            builder.restClientBuilderCustomizers(customizers);
        }
        return builder.build();
    }


}
