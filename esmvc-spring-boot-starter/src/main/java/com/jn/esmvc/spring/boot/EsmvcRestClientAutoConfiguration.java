package com.jn.esmvc.spring.boot;

import com.jn.esmvc.service.ESRestClient;
import com.jn.esmvc.service.ESRestClientBuilder;
import com.jn.esmvc.service.config.rest.DefaultRestClientBuilderCustomizer;
import com.jn.esmvc.service.config.rest.EsmvcRestClientProperties;
import com.jn.esmvc.service.config.rest.RestClientBuilderCustomizer;
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
import java.util.stream.Collectors;

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
            ObjectProvider<RestClientBuilderCustomizer> builderCustomizers) {
        return new ESRestClientBuilder()
                .properties(esmvcProperties)
                .restClientBuilderCustomizers(builderCustomizers.stream().collect(Collectors.toList()))
                .build();
    }


}
