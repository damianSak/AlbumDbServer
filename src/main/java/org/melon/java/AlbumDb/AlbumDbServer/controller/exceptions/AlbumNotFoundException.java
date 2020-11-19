package org.melon.java.AlbumDb.AlbumDbServer.controller.exceptions;

public class AlbumNotFoundException extends Exception {
    public AlbumNotFoundException() {
    }

    public AlbumNotFoundException(String message) {
        super(message);
    }

    public AlbumNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlbumNotFoundException(Throwable cause) {
        super(cause);
    }
}
