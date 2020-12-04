package org.melon.java.AlbumDb.AlbumDbServer.controller;

import org.melon.java.AlbumDb.AlbumDbServer.controller.errors.AlbumDbAppError;
import org.melon.java.AlbumDb.AlbumDbServer.controller.exceptions.AlbumAlreadyInDatabaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class AlbumControllerAdvice {

    @Value("${albumdbserver.sendreport.uri}")
    private String sendReportUri;
    @Value("${albumdbserver.api.version}")
    private String currentApiVersion;

    @ExceptionHandler(AlbumAlreadyInDatabaseException.class)
    public ResponseEntity<AlbumDbAppError> handleNonExistingHero(HttpServletRequest request,
                                                                 AlbumAlreadyInDatabaseException ex) {
        final AlbumDbAppError error = new AlbumDbAppError(
                currentApiVersion,
                ex.getErrorCode(),
                "This superhero is hiding in the cave",
                "superhero-exceptions",
                "You can't find this superhero right now. Try later.",
                "Saving someone",
                sendReportUri
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
