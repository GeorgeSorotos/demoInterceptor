package org.acme.interceptor;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.acme.interceptor.handlers.HeaderValidationHandler;

import java.util.List;

@HeaderValidation
@Priority(2020)
@Interceptor
@Slf4j
public class HeaderInterceptorInterceptor {

    private final HttpHeaders httpHeaders;
    private final List<HeaderValidationHandler> headerValidationHandlers;

    @Inject
    public HeaderInterceptorInterceptor(HttpHeaders httpHeaders, Instance<HeaderValidationHandler> headerValidationHandlers) {
        this.httpHeaders = httpHeaders;
        this.headerValidationHandlers = headerValidationHandlers.stream().toList();
    }

    @AroundInvoke
    Object validateHeader(InvocationContext context) throws Exception {

        headerValidationHandlers.stream()
                .filter(handler -> handler.canHandle(httpHeaders))
                .forEach(handler -> handler.validate(httpHeaders));

        return context.proceed();
    }
}