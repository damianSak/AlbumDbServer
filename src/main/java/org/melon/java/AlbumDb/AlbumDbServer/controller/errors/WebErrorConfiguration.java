package org.melon.java.AlbumDb.AlbumDbServer.controller.errors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebErrorConfiguration {

    @Value("${albumdbserver.api.version}")
    private String currentApiVersion;
    @Value("${albumdbserver.sendreport.uri}")
    private String sendReportUri;

    /**
     * We override the default {@link }
     *
     * @return A custom implementation of ErrorAttributes
     */
    @Bean
    public ErrorAttributes errorAttributes() {
        return new AlbumDbAppErrorAttributes(currentApiVersion, sendReportUri);
    }

}
