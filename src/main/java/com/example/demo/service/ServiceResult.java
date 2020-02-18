package com.example.demo.service;

public class ServiceResult {
    private Status status = Status.SUCCESS;
    private String message;
    private Object data;

    public enum Status {
        SUCCESS, FAILED;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
