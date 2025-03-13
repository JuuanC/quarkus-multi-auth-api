package org.auth.common.dto.response;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;

@RegisterForReflection
public class CustomResponse<T> {

    String message;
    LocalDateTime createdRequest;
    String uuid;
    T data;

    public CustomResponse() { }

    public CustomResponse(String message, LocalDateTime createdRequest, String uuid, T data) {
        this.message = message;
        this.createdRequest = createdRequest;
        this.uuid = uuid;
        this.data = data;
    }

    private CustomResponse(Builder<T> builder) {
        this.message = builder.message;
        this.createdRequest = builder.createdRequest;
        this.uuid = builder.uuid;
        this.data = builder.data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedRequest() {
        return createdRequest;
    }

    public void setCreatedRequest(LocalDateTime createdRequest) {
        this.createdRequest = createdRequest;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "message='" + message + '\'' +
                ", createdRequest=" + createdRequest +
                ", uuid='" + uuid + '\'' +
                ", data=" + data +
                '}';
    }

    public static class Builder<T> {

        private String message;
        private LocalDateTime createdRequest;
        private String uuid;
        private T data;

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> createdRequest(LocalDateTime createdRequest) {
            this.createdRequest = createdRequest;
            return this;
        }

        public Builder<T> uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public CustomResponse<T> build() {
            return new CustomResponse<>(this);
        }
    }
}
