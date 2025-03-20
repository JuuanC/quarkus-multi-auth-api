package org.auth.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.auth.common.utils.GenerateUUID;
import org.auth.persistence.entity.UserEntity;
import org.auth.service.UserService;

@Path("/user")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response save(UserEntity request, @HeaderParam("X-Request-ID") String requestId) {
        // Si no se proporciona UUID, se genera uno nuevo
        String traceId = GenerateUUID.generate(requestId);
        userService.save(request, traceId);
        return Response.ok()
                .header("X-Request-ID", traceId)
                .build();
    }

    @PUT
    public Response updateEmail(@QueryParam("username") String username, @QueryParam("email") String request,
                            @HeaderParam("X-Request-ID") String requestId) {
        // Si no se proporciona UUID, se genera uno nuevo
        String traceId = GenerateUUID.generate(requestId);
        userService.updateEmail(username, request, traceId);
        return Response.ok()
                .header("X-Request-ID", traceId)
                .build();
    }

    @DELETE
    public Response delete(@QueryParam("username") String request,
                       @HeaderParam("X-Request-ID") String requestId) {
        // Si no se proporciona UUID, se genera uno nuevo
        String traceId = GenerateUUID.generate(requestId);
        userService.delete(request, traceId);
        return Response.ok()
                .header("X-Request-ID", traceId)
                .build();
    }

    @GET
    @Path("/getById")
    public Response getById(@QueryParam("username") String request,
                              @HeaderParam("X-Request-ID") String requestId) {
        // Si no se proporciona UUID, se genera uno nuevo
        String traceId = GenerateUUID.generate(requestId);
        return Response.ok(userService.getByUsername(request, traceId))
                .header("X-Request-ID", traceId)
                .build();
    }

    @GET
    @Path("/getAll")
    public Response getAll(@HeaderParam("X-Request-ID") String requestId) {
        // Si no se proporciona UUID, se genera uno nuevo
        String traceId = GenerateUUID.generate(requestId);
        return Response.ok(userService.getAll(traceId))
                .header("X-Request-ID", traceId)
                .build();
    }
}
