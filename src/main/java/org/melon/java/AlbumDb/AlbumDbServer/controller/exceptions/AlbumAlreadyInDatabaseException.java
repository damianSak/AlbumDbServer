package org.melon.java.AlbumDb.AlbumDbServer.controller.exceptions;

import org.melon.java.AlbumDb.AlbumDbServer.controller.errors.ErrorCode;

public class AlbumAlreadyInDatabaseException extends RuntimeException implements ErrorCode {

    public AlbumAlreadyInDatabaseException(String message) {
        super(message);
    }
    public String getErrorCode(){
        return "Error mój";
    }
}
