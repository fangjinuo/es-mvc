package com.jn.esmvc.spring.boot;

import com.jn.esmvc.service.config.EsmvcProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnMissingBean
@Configuration
public class EsmvcAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public EsmvcProperties esmvcProperties(){
        return new EsmvcProperties();
    }


}
