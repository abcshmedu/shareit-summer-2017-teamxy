package edu.hm.schatter.shareit.businesslayer;

import javax.ws.rs.core.Response;

public enum MediaServiceResult {
    OK(200, "OK", Response.Status.OK),
    ALREADY_EXISTS(400, "The entity you wanted to create already exists", Response.Status.BAD_REQUEST),
    INVALID_INFORMATION(400, "The information you provided is invalid", Response.Status.BAD_REQUEST),
    MISSING_INFORMATION(400, "Some required information is missing. (e.g. title, author, etc.)", Response.Status.NOT_FOUND),
    NOT_FOUND(404, "The requested resource could not be found", Response.Status.BAD_REQUEST),
    ERROR(500, "Internal Server Error", Response.Status.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final Response.Status status;

    MediaServiceResult(int code, String message, Response.Status status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Response.Status getStatus() {
        return status;
    }

    public String getJSON() {
        return "{\"code\": " + code + ", \"message\": \"" + message + "\"}";
    }
}
