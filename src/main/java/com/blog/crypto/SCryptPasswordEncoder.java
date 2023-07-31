package com.blog.crypto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("default")
@Component
public class SCryptPasswordEncoder implements PasswordEncoder{

    private static final org.springframework.security.crypto.scrypt.SCryptPasswordEncoder encoder = new org.springframework.security.crypto.scrypt.SCryptPasswordEncoder(
            16,
            8,
            1,
            32,
            64);

    @Override
    public String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }
}
