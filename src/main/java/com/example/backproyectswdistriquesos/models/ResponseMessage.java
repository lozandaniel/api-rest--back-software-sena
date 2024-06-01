package com.example.backproyectswdistriquesos.models;

import java.util.Optional;

public class ResponseMessage<T> {
    private int statusCode;
    private String message;

    private Optional<T> data;

    public ResponseMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseMessage(int statusCode, String message, Optional<T> data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
    public ResponseMessage(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = Optional.ofNullable(data);
    }



    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Optional<T> getData() {
        return data;
    }

    public void setData(Optional<T> data) {
        this.data = data;
    }
}
