package com.jn.esmvc.spring.boot;

import com.jn.esmvc.model.ESModelIdGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESModelIdAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = "esModelIdGenerator")
    public ESModelIdGenerator esModelIdGenerator() {
        return new ESModelIdGenerator();
    }
}
