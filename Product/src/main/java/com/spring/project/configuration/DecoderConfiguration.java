package com.spring.project.configuration;

import com.spring.project.utils.ProductErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecoderConfiguration {

    @Bean
    public ErrorDecoder decoder(){
        return new ProductErrorDecoder();
    }
}
