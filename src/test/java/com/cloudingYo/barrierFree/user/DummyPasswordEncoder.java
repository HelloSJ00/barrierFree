package com.cloudingYo.barrierFree.user;

import org.springframework.security.crypto.password.PasswordEncoder;

public class DummyPasswordEncoder implements PasswordEncoder {
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
