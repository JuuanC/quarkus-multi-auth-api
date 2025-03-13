package org.auth.common.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CustomException extends RuntimeException {

    private String code;
    private String message;
    private Object data;
    private String uuid;

    public CustomException(String code, String message, String uuid) {
        super(message);
        this.code = code;
        this.uuid = uuid;
    }

    public CustomException(String code, String message, Object data, String uuid) {
        super(message);
        this.code = code;
        this.data = data;
        this.uuid = uuid;
    }

    public CustomException(String code, String message, String uuid, Exception e) {
        super(message, e);
        this.code = code;
        this.uuid = uuid;
    }

    public CustomException(String code, String message, Object data, String uuid, Exception e) {
        super(message, e);
        this.code = code;
        this.data = data;
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}

