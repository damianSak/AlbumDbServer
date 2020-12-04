package org.melon.java.AlbumDb.AlbumDbServer.controller.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AlbumDbResponse extends ResponseEntity<String> {

    private String message;

    private AlbumDbResponse(String string, String message, HttpStatus status) {
        super(string, status);
        this.message = message;
    }

    public static AlbumDbResponse ok(String string,String message) {
        return new AlbumDbResponse(string, null, HttpStatus.OK);
    }

    public static AlbumDbResponse fail(String response, String message) {
        return new AlbumDbResponse(response, message, HttpStatus.CONFLICT);
    }

    public String getMessage() {
        return message;
    }
}
