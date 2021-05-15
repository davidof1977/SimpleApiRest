package io.swagger.api;


import io.swagger.api.SimpleapiApiService;
import io.swagger.api.factories.SimpleapiApiServiceFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;


@Path("/simpleapi")


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2021-05-15T07:33:01.136Z[GMT]")public class SimpleapiApi  {
   private final SimpleapiApiService delegate;

   public SimpleapiApi(@Context ServletConfig servletContext) {
      SimpleapiApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("SimpleapiApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (SimpleapiApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = SimpleapiApiServiceFactory.getSimpleapiApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/{numero}")
    
    @Produces({ "application/json" })
    @Operation(summary = "", description = "Prueba.", tags={ "consulta" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "ok", content = @Content(schema = @Schema(implementation = Object.class))),
        
        @ApiResponse(responseCode = "400", description = "Petición incorrecta"),
        
        @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
    public Response getNumero(@Parameter(in = ParameterIn.PATH, description = "Numérico de cuatro posiciones.",required=true) @PathParam("numero") Integer numero
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getNumero(numero,securityContext);
    }
}
