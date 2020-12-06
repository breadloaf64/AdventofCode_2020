package Days;

import Helpers.InputHandler;
import Helpers.Regex;
import Helpers.StringList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day4 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_4/input.txt");
        //test(input);
        int validCount = 0, validCountStrict = 0;
        ArrayList<String> passports = StringList.collectLines(input, " ");
        for (String p : passports) {
            if(passportValid(p, false)) validCount++;
            if(passportValid(p, true)) validCountStrict++;
        }
        System.out.println("Day 4: " + validCount + ", " + validCountStrict);
    }

    private static void test(ArrayList<String> input) {

    }

    private static boolean passportValid(String passport, boolean validateData) {
        String delims = "[ ]+";
        String[] tokens = passport.split(delims);
        boolean[] validField = new boolean[8];
        Arrays.fill(validField, false);
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if(token.length() > 0) {
                processToken(token, validField, validateData);
            }
        }
        for(int i = 0; i < validField.length - 1; i++) {
            if (!validField[i]) return false;
        }
        return true;
    }

    private static void processToken(String token, boolean[] validField, boolean validateData) {
        String delim = "[:]";
        String field = token.split(delim)[0];
        String value = token.split(delim)[1];

        switch (field) {
            case "byr" :
                if(validateData) validField[0] = valid_byr(value);
                else validField[0] = true;
                break;

            case "iyr" :
                if(validateData) validField[1] = valid_iyr(value);
                else validField[1] = true;
                break;

            case "eyr" :
                if(validateData) validField[2] = valid_eyr(value);
                else validField[2] = true;
                break;

            case "hgt" :
                if(validateData) validField[3] = valid_hgt(value);
                else validField[3] = true;
                break;

            case "hcl" :
                if(validateData) validField[4] = Regex.test("#([a-f]|\\d){6}", value);
                else validField[4] = true;
                break;

            case "ecl" :
                if(validateData) validField[5] = Regex.test("(amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth)", value);
                else validField[5] = true;
                break;

            case "pid" :
                if(validateData) validField[6] = Regex.test("\\d{9}", value);
                else validField[6] = true;
                break;

            case "cid" :
                validField[7] = true;
                break;
        }
    }

    private static boolean valid_byr(String value) {
        int byr = Integer.parseInt(value);
        if(1920 <= byr & byr <= 2002) return true;
        return false;
    }

    private static boolean valid_iyr(String value) {
        int iyr = Integer.parseInt(value);
        if(2010 <= iyr & iyr <= 2020) return true;
        return false;
    }

    private static boolean valid_eyr(String value) {
        int iyr = Integer.parseInt(value);
        if(2020 <= iyr & iyr <= 2030) return true;
        return false;
    }

    private static boolean valid_hgt(String value) {
        String strHeight = "", strUnit = "";
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if(48 <= c & c <= 57) strHeight += c;
            else strUnit += c;
        }
        int hgt = Integer.parseInt(strHeight);
        if (strUnit.contains("cm") && 150 <= hgt && hgt <= 195) return true;
        else if (strUnit.contains("in") && 59 <= hgt && hgt <= 76) return true;
        else return false;
    }
}
