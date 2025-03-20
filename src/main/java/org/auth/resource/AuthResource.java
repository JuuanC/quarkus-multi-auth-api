package org.auth.resource;

import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.auth.common.dto.request.LoginRequest;
import org.auth.common.utils.GenerateUUID;
import org.auth.service.AuthService;

import java.util.UUID;

@Path("")
public class AuthResource {

    private final AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(LoginRequest loginRequest,
                          @HeaderParam("X-Request-ID") String requestId) {
        // Si no se proporciona UUID, se genera uno nuevo
        String traceId = GenerateUUID.generate(requestId);

        authService.login(loginRequest, traceId);
        return Response.ok()
                .header("X-Request-ID", traceId)
                .build();
    }

    @POST
    @Path("/logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(String username,
                          @HeaderParam("X-Request-ID") String requestId) {

        // Si no se proporciona UUID, se genera uno nuevo
        String traceId = GenerateUUID.generate(requestId);

        authService.logout(username, traceId);
        return Response.ok()
                .header("X-Request-ID", traceId)
                .build();
    }

    @POST
    @Path("refresh")
    @Produces(MediaType.TEXT_PLAIN)
    public Response refresh(String token,
                          @HeaderParam("X-Request-ID") String requestId) {

        // Si no se proporciona UUID, se genera uno nuevo
        String traceId = GenerateUUID.generate(requestId);

        authService.refresh(token, traceId);
        return Response.ok()
                .header("X-Request-ID", traceId)
                .build();
    }
}
