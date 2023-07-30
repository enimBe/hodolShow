package com.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

//@Configuration
@Data
@ConfigurationProperties(prefix = "hodolman")
public class AppConfig {

    public Hello hello;

    @Data
    public static class Hello {
        public String name;
        public String home;
        public String hobby;
        public Long age;
    }

}
