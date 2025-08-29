package org.acme.resource.interceptor.handlers;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.extern.slf4j.Slf4j;


import java.util.Optional;

@ApplicationScoped
@Slf4j
public class TestHeaderHandler implements HeaderValidationHandler {
    private static final String testHeaderName = "testHeader";
    private static final String testHeaderValue = "correct";


    @Override
    public boolean canHandle(HttpHeaders httpHeaders) {
        return Optional.ofNullable(httpHeaders.getHeaderString(testHeaderName))
                .isPresent();
    }

    @Override
    public void validate(HttpHeaders httpHeaders) {
        String headerValue = httpHeaders.getHeaderString(testHeaderName);
        if (!StringUtil.isNullOrEmpty(headerValue) && !testHeaderValue.equals(headerValue)) {
            log.error("Receive {} but expected {}", headerValue, testHeaderValue);
        }
    }
}
