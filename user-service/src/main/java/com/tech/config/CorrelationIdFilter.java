package com.tech.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter implements Filter {

    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    public static final String MDC_KEY = "correlationId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String correlationId = httpServletRequest.getHeader(CORRELATION_ID_HEADER);

        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = generateCorrelationId();
        }

        MDC.put(MDC_KEY, correlationId);
        httpServletResponse.setHeader(CORRELATION_ID_HEADER, correlationId);

        try {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            MDC.remove(MDC_KEY);
        }
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Initialization, if needed
    }

    @Override
    public void destroy() {
        // Cleanup, if needed
    }
}
