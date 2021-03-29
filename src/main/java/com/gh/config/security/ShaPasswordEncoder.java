package com.gh.config.security;

import com.gh.utils.auth.Crypto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ShaPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return Crypto.sha256(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Crypto.sha256(rawPassword.toString()).equals(encodedPassword);
    }
}
