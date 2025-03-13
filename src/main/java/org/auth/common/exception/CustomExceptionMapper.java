package org.auth.common.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.auth.common.dto.response.CustomResponse;

import java.time.LocalDateTime;

@Provider
class CustomExceptionMapper implements ExceptionMapper<CustomException> {

    @Override
    public Response toResponse(CustomException exception) {
        CustomResponse<Object> errorResponse = new CustomResponse.Builder<>()
                .message(exception.getMessage())
                .uuid(exception.getUuid())
                .createdRequest(LocalDateTime.now())
                .data(exception.getData())
                .build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }

}

