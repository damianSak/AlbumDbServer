package org.melon.java.AlbumDb.AlbumDbServer.controller.errors;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class AlbumDbAppErrorAttributes extends DefaultErrorAttributes {
    private final String currentApiVersion;
    private final String sendReportUri;

    public AlbumDbAppErrorAttributes(final String currentApiVersion, final String sendReportUri) {
        this.currentApiVersion = currentApiVersion;
        this.sendReportUri = sendReportUri;
    }

    @Override
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest, final boolean includeStackTrace) {
        final Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, false);
        final AlbumDbAppError albumDBAppError = AlbumDbAppError.fromDefaultAttributeMap(
                currentApiVersion, defaultErrorAttributes, sendReportUri
        );
        return albumDBAppError.toAttributeMap();
    }
}
