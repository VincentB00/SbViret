package com.vincent.password_manager.http;

import javax.servlet.http.HttpServletResponse;

public class Response {

    private boolean success;
    private int status;
    private String message;

    public Response() {
        super();
    }

    public Response(boolean success) {
        super();
        this.success = success;
        this.status = success ? 200 : 400;
        this.message = "";
    }

    public Response(boolean success, String message) {
        super();
        this.success = success;
        this.status = success ? 200 : 400;
        this.message = message;
    }

    public Response(boolean success, int code, String message) {
        super();
        this.success = success;
        this.status = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int code) {
        this.status = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response setStatus(HttpServletResponse response)
    {
        response.setStatus(this.status);
        return this;
    }

    @Override
    public String toString() {
        return "Response [success=" + success + ", code=" + status + ", message=" + message + "]";
    }

}