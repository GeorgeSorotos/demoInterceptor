package org.acme.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.acme.interceptor.HeaderValidation;

@Path("/hello")
@Slf4j
@HeaderValidation
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@HeaderParam("testHeader") String testHeader, @HeaderParam("testHeader1") String testHeader1) {
        return "Hello from Quarkus REST";
    }
}
