package com.ravi.commerce.common;

import android.content.Context;
import android.net.ConnectivityManager;

import com.ravi.commerce.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static List<Integer> imgList = new ArrayList<Integer>();
    public static List<String> nameList = new ArrayList<String>();
    public static String BASE_URL = "https://simplifiedcoding.net/demos/";
    public static int SPLASH_TIME = 5000;
    public static String LOGIN;
    public static String PASS;

    public static boolean isValidUsername(String name) {
        String regex = "^[A-Za-z]\\w{5,29}$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }


    public static boolean isPasswordValid(String password) {
        String regExpn = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        CharSequence inputStr = password;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }


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

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static List<String> getName() {
        nameList.clear();
        nameList.add("Hindi");
        nameList.add("English");
        nameList.add("Maths");
        nameList.add("CS");
        return nameList;
    }

    public static List<Integer> getImg() {
        imgList.clear();
        imgList.add(R.drawable.one);
        imgList.add(R.drawable.one);
        imgList.add(R.drawable.one);
        imgList.add(R.drawable.one);
        return imgList;
    }
}
