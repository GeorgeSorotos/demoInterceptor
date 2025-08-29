package org.acme.interceptor.handlers;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@ApplicationScoped
public class TestHeader1Handler implements HeaderValidationHandler {
    private static final String testHeader1Name = "testHeader1";
    private static final String testHeader1Value = "correct1";


    @Override
    public boolean canHandle(HttpHeaders httpHeaders) {
        return Optional.ofNullable(httpHeaders.getHeaderString(testHeader1Name))
                .isPresent();
    }

    @Override
    public void validate(HttpHeaders httpHeaders) {
        String headerValue = httpHeaders.getHeaderString(testHeader1Name);
        if (!StringUtil.isNullOrEmpty(headerValue) && !testHeader1Value.equals(headerValue)) {
            log.error("Receive {} but expected {}", headerValue, testHeader1Value);
        }
    }
}
