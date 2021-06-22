package complexprogrammer.uz.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isValidEmail(CharSequence target) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (target.toString().trim().matches(emailPattern))
        {
           return  true;
        }
        else
        {
            return false;
        }
//        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(password);
        return matcher.matches();
    }
    public static boolean isValidTextFiels(String text) {
        if(text.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
}
