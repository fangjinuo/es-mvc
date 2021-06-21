package com.jn.esmvc.spring.boot;

import com.jn.esmvc.service.rest.config.EsmvcRestClientProperties;
import com.jn.esmvc.service.rest.security.searchguard.SearchGuardRestClientBuilderCustomizer;
import com.jn.esmvc.service.security.searchguard.propertis.SearchGuardSSLProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name = "esmvc.rest.primary.search-guard-ssl.enabled", havingValue = "true", matchIfMissing = false)
@ConditionalOnBean(EsmvcRestClientProperties.class)
@Configuration("searchGuardRestClientAutoConfiguration")
public class SearchGuardRestClientAutoConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "esmvc.rest.primary.search-guard-ssl")
    public SearchGuardSSLProperties searchGuardSSLProperties() {
        return new SearchGuardSSLProperties();
    }

    @Bean
    public SearchGuardRestClientBuilderCustomizer searchGuardRestClientBuilderCustomizer(SearchGuardSSLProperties searchGuardSSLProperties, EsmvcRestClientProperties esmvcRestClientProperties) {
        SearchGuardRestClientBuilderCustomizer searchGuardConfig = new SearchGuardRestClientBuilderCustomizer();
        searchGuardConfig.setSearchGuardSSLProperties(searchGuardSSLProperties);
        searchGuardConfig.setEsmvcRestClientProperties(esmvcRestClientProperties);
        return searchGuardConfig;
    }


}
