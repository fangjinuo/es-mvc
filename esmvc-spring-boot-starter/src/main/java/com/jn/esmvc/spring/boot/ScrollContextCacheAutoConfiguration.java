package com.jn.esmvc.spring.boot;

import com.jn.esmvc.service.config.ScrollCacheProperties;
import com.jn.esmvc.service.scroll.ScrollContextCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfigureBefore({EsmvcTransportClientAutoConfiguration.class, EsmvcRestClientAutoConfiguration.class})
@ConditionalOnBean(name = "scrollContextCacheAutoConfiguration")
@Configuration
public class ScrollContextCacheAutoConfiguration {

    @ConditionalOnMissingBean(name = "scrollCacheProperties")
    @Bean("scrollCacheProperties")
    @ConfigurationProperties(prefix = "esmvc.scrollcache")
    public ScrollCacheProperties scrollCacheProperties() {
        return new ScrollCacheProperties();
    }

    @ConditionalOnMissingBean(name = "scrollContextCache")
    @Bean("scrollContextCache")
    @Autowired
    public ScrollContextCache scrollContextCache(@Qualifier("scrollCacheProperties") ScrollCacheProperties cacheProperties) {
        ScrollContextCache cache = new ScrollContextCache();
        cache.setExpireInSeconds(cacheProperties.getLocalCacheExpireInSeconds());
        cache.setMaxCapacity(cacheProperties.getLocalCacheMaxCapacity());
        cache.init();
        return cache;
    }

}
