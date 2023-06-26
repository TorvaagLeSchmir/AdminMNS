package com.projet.filrouge.Services;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generatePassword(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_CHARACTERS.length());
            char randomChar = ALPHANUMERIC_CHARACTERS.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }
        return passwordBuilder.toString();
    }
}