package io.swagger.api.factories;

import io.swagger.api.SimpleapiApiService;
import io.swagger.api.impl.SimpleapiApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2021-05-15T07:33:01.136Z[GMT]")public class SimpleapiApiServiceFactory {
    private final static SimpleapiApiService service = new SimpleapiApiServiceImpl();

    public static SimpleapiApiService getSimpleapiApi() {
        return service;
    }
}
