package com.tech.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import static com.tech.config.CorrelationIdFilter.CORRELATION_ID_HEADER_NAME;
import static com.tech.config.CorrelationIdFilter.MDC_KEY;


@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String correlationId = MDC.get(MDC_KEY);
        if (correlationId != null) {
            requestTemplate.header(CORRELATION_ID_HEADER_NAME, correlationId);
        }
    }
}
