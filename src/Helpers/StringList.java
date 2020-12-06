package Helpers;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StringList {

    public static ArrayList<String> collectLines(ArrayList<String> input, String delimiter) {
        int j = 0; // indexes the collected array
        ArrayList<String> collected = new ArrayList();
        collected.add("");
        for (String s : input) {
            if(s.length() == 0) {
                j++;
                collected.add("");
            }
            else collected.set(j, collected.get(j) + delimiter + s);
        }
        return collected;
    }

    public static int countUnique(String s) {
        int count = 0;
        ArrayList<Character> seen = new ArrayList();
        for(int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (!seen.contains(current)) {
                seen.add(current);
                count++;
            }
        }
        return count;
    }

    public static int countOccurences(String s, char c) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) count++;
        }
        return count;
    }

    public static ArrayList<Character> uniqueCharacters(String s) {
        ArrayList<Character> uniqueCharacters = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (!uniqueCharacters.contains(current)) uniqueCharacters.add(current);
        }
        return uniqueCharacters;
    }
}
