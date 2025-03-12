package org.auth.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.auth.service.GenerateToken;

@Path("/security")
public class AuthResource {

   @Inject
   GenerateToken generateToken;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@Context SecurityContext ctx) {
        return Response.ok("This endpoint is secured with both JWT and Basic Auth").build();
    }

    @GET
    @Path("token")
    @Produces(MediaType.TEXT_PLAIN)
    public String generateToken(@QueryParam("email") String email, @QueryParam("rol") String rol) {
        return generateToken.generate(email, rol);
    }
}
