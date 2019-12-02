package com.test.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReponseBodyConfig {

    @Bean
    public ResponseBodyWrapper responseBodyWrapper(){
        return new ResponseBodyWrapper();
    }
}
