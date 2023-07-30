package com.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Base64;

//@Configuration
@Data
@ConfigurationProperties(prefix = "hodolman")
public class AppConfig {

    private byte[] jwkKey;

    public void setJwkKey(String jwkKey) {
        this.jwkKey = Base64.getDecoder().decode(jwkKey);
    }

    public byte[] getJwkKey() {
        return jwkKey;
    }
}
