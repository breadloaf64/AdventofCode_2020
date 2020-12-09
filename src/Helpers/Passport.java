package Helpers;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Objects;

public class Passport {
    String data;
    Hashtable<String, Boolean> fieldsValid;
    Hashtable<String, Boolean> fieldsPresent;

    public Passport(String data) {
        this.data = data;
        initialiseFieldsPresent();
        initialiseFieldsValid();
    }

    private void initialiseFieldsPresent() {
        fieldsPresent = new Hashtable();

        fieldsPresent.put("byr", false);
        fieldsPresent.put("iyr", false);
        fieldsPresent.put("eyr", false);
        fieldsPresent.put("hgt", false);
        fieldsPresent.put("hcl", false);
        fieldsPresent.put("ecl", false);
        fieldsPresent.put("pid", false);
        fieldsPresent.put("cid", false);

        String[] tokens = data.split("[ ]+");
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if(token.length() > 0) {
                String field = token.split(":")[0];
                fieldsPresent.put(field, true);
            }
        }
    }

    public boolean valid() {
        boolean valid = true;
        for (String key : fieldsPresent.keySet()) {
            if (!Objects.equals(key, "cid")) {
                if (!fieldsPresent.get(key)) valid = false;
            }
        }
        return valid;
    }

    public boolean validStrict() {
        boolean valid = true;
        for (String key : fieldsValid.keySet()) {
            if (!Objects.equals(key, "cid")) {
                if (!fieldsValid.get(key)) valid = false;
            }
        }
        return valid;
    }

    private void initialiseFieldsValid() {
        fieldsValid = new Hashtable();

        fieldsValid.put("byr", false);
        fieldsValid.put("iyr", false);
        fieldsValid.put("eyr", false);
        fieldsValid.put("hgt", false);
        fieldsValid.put("hcl", false);
        fieldsValid.put("ecl", false);
        fieldsValid.put("pid", false);
        fieldsValid.put("cid", false);

        String[] tokens = data.split("[ ]+");
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if(token.length() > 0) {
                String field = token.split(":")[0];
                String value = token.split(":")[1];
                fieldsValid.put(field, validateFieldValue(field, value));
            }
        }
    }

    public static boolean validateFieldValue(String field, String value) {
        boolean valid = false;
        switch (field) {
            case "byr" :
                int byr = Integer.parseInt(value);
                valid = (1920 <= byr & byr <= 2002);
                break;

            case "iyr" :
                int iyr = Integer.parseInt(value);
                valid = (2010 <= iyr & iyr <= 2020);
                break;

            case "eyr" :
                int eyr = Integer.parseInt(value);
                valid = (2020 <= eyr & eyr <= 2030);
                break;

            case "hgt" :
                valid = valid_hgt(value);
                break;

            case "hcl" :
                valid = Regex.test("#([a-f]|\\d){6}", value);
                break;

            case "ecl" :
                valid = Regex.test("(amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth)", value);
                //else validField[5] = true;
                break;

            case "pid" :
                valid = Regex.test("\\d{9}", value);
                break;

            case "cid" :
                valid = true;
                break;
        }
        return valid;
    }

    private static boolean valid_hgt(String value) {
        String strHeight = "", strUnit = "";
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if(48 <= c & c <= 57) strHeight += c;
            else strUnit += c;
        }
        int hgt = Integer.parseInt(strHeight);
        if (strUnit.contains("cm") && 150 <= hgt && hgt <= 193) return true;
        else if (strUnit.contains("in") && 59 <= hgt && hgt <= 76) return true;
        else return false;
    }
}
