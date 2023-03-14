package com.ninjaone.backendinterviewproject.controller.handler;

import java.util.Date;

public class EndpointErrorMessage {

    private String message;
    private String status;
    private String time;

    public EndpointErrorMessage(String message, String status, String time) {
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public EndpointErrorMessage(String message, String status) {
        this.message = message;
        this.status = status;
        this.time = new Date().toString();
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }
}
