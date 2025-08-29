package org.acme.resource.interceptor.handlers;


import jakarta.ws.rs.core.HttpHeaders;

public interface HeaderValidationHandler {
    boolean canHandle(HttpHeaders httpHeaders);
    void validate(HttpHeaders httpHeaders);
}
