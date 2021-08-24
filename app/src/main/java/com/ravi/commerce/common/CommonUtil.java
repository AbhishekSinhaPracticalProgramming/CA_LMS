package com.ravi.commerce.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static String BASE_URL = "https://simplifiedcoding.net/demos/";
    public static final int SPLASH_TIME = 5000;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static boolean emailValidate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean isFullname(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }
}
