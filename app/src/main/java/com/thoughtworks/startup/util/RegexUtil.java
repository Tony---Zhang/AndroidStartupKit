package com.thoughtworks.startup.util;

import java.util.regex.Pattern;

public class RegexUtil {

    private static final Pattern THOUGHT_WORKS_EMAIL_PATTERN = Pattern.compile("^[\\w+]{1,256}\\@tw\\.com$");

    public static boolean isThoughtWorkEmail(String email) {
        return THOUGHT_WORKS_EMAIL_PATTERN.matcher(email).matches();
    }

}
