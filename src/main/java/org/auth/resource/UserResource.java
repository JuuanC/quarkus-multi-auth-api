package org.auth.resource;

import jakarta.ws.rs.*;
import org.auth.persistence.entity.UserEntity;
import org.auth.service.UserService;

import java.util.List;

@Path("/user")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    public void save(UserEntity request) {
        userService.save(request);
    }

    @PUT
    public void updateEmail(@QueryParam("username") String username, @QueryParam("email") String request) {
        userService.updateEmail(username, request);
    }

    @DELETE
    public void delete(@QueryParam("username") String request) {
        userService.delete(request);
    }

    @GET
    @Path("/getById")
    public UserEntity getById(@QueryParam("username") String request) {
        return userService.getByUsername(request);
    }

    @GET
    @Path("/getAll")
    public List<UserEntity> getAll() {
        return userService.getAll();
    }
}
