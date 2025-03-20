package org.auth.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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

    @GET
    @Path("/getAll")
    public List<UserEntity> getAll() {
        return userService.getAll();
    }
}
