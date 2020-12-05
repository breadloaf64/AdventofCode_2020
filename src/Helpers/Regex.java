package Helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean test(String reg, String input) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(input);
        boolean matchFound = matcher.find();
        if(matchFound) return true;
        else return false;
    }
}
