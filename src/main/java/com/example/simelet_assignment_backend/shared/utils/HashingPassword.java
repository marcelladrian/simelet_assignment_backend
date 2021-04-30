package com.example.simelet_assignment_backend.shared.utils;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HashingPassword {
    private final String pepper = "pepper";
    private final int iterations = 200000;
    private final int hashWidth = 256;
    private final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder(pepper, iterations, hashWidth);

    public String generateHashPassword(String password){
        return generatePassword(password);
    }

    public boolean matching(String raw_password, String stored_password){
        return matchPassword(raw_password, stored_password);
    }

    private String generatePassword(String password){
        pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
        return pbkdf2PasswordEncoder.encode(password);
    }

    private boolean matchPassword(String raw_password, String stored_password){
        pbkdf2PasswordEncoder.setEncodeHashAsBase64(true);
        return pbkdf2PasswordEncoder.matches(raw_password, stored_password);
    }
}
