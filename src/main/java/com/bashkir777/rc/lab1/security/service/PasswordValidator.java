package com.bashkir777.rc.lab1.security.service;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final Pattern UPPERCASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]");
    private static final Pattern WHITESPACE = Pattern.compile("\\s");

    public static void validateUsernameAndPassword(String username, String rawPassword) {
        if (username == null || username.length() < 5) {
            throw new IllegalArgumentException("Username must contain at least 5 symbols");
        }

        if (rawPassword == null || rawPassword.length() < 8) {
            throw new IllegalArgumentException("Password must contain at least 8 symbols");
        }

        if (!UPPERCASE.matcher(rawPassword).find()) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }

        if (!LOWERCASE.matcher(rawPassword).find()) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter");
        }

        if (!DIGIT.matcher(rawPassword).find()) {
            throw new IllegalArgumentException("Password must contain at least one digit");
        }

        if (!SPECIAL_CHAR.matcher(rawPassword).find()) {
            throw new IllegalArgumentException("Password must contain at least one special character (e.g. !@#$%^&*)");
        }

        if (WHITESPACE.matcher(rawPassword).find()) {
            throw new IllegalArgumentException("Password must not contain whitespace characters");
        }
    }
}
