package com.hh.record.config.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google.auth")
public class GoogleAuthComponent {

    private String clientId;
    private String clientSecret;
    private String grantType;
    private String baseUrl;
    private String tokenUrl;
    private String profileUrl;

}
