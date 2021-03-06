package Days;

import Helpers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day4 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_4/input.txt");
        Tuple<Integer, Integer> counts = countValidPassports(input);
        System.out.println("Day 4: " + counts.x + " " + counts.y);
    }

    private static Tuple<Integer, Integer> countValidPassports(ArrayList<String> input) {
        Tuple<Integer, Integer> counts = new Tuple<Integer, Integer>(0, 0);
        ArrayList<String> strPassports = StringList.collectLines(input, " ");
        ArrayList<Passport> passports = makePassports(strPassports);
        for (Passport p : passports) {
            if (p.valid()) counts.x++;
            if (p.validStrict()) {
                counts.y++;
            }
        }
        return counts;
    }

    private static ArrayList<Passport> makePassports (ArrayList<String> input) {
        ArrayList<Passport> passports = new ArrayList();
        for (String strPassport : input) {
            passports.add(new Passport(strPassport));
        }
        return passports;
    }


}
