package com.tech.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

import static com.tech.config.CorrelationIdFilter.CORRELATION_ID_HEADER;
import static com.tech.config.CorrelationIdFilter.MDC_KEY;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor correlationIdInterceptor() {
        return template -> {
            Optional.ofNullable(MDC.get(MDC_KEY))
                    .ifPresent(correlationId -> template.header(CORRELATION_ID_HEADER, correlationId));
        };
    }
}
