package com.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
<<<<<<< HEAD

import java.util.Base64;
=======
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
>>>>>>> origin/main

//@Configuration
@Data
@ConfigurationProperties(prefix = "hodolman")
public class AppConfig {

<<<<<<< HEAD
    private byte[] jwkKey;

    public void setJwkKey(String jwkKey) {
        this.jwkKey = Base64.getDecoder().decode(jwkKey);
    }

    public byte[] getJwkKey() {
        return jwkKey;
    }
=======
    public Hello hello;

    @Data
    public static class Hello {
        public String name;
        public String home;
        public String hobby;
        public Long age;
    }

>>>>>>> origin/main
}
