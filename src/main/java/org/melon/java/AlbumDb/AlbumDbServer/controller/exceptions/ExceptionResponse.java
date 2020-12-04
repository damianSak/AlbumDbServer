package org.melon.java.AlbumDb.AlbumDbServer.controller.exceptions;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private int code;

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public ExceptionResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ExceptionResponse(Date timestamp, String message, int code) {
        this.timestamp = timestamp;
        this.message = message;
        this.code = code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
