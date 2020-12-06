package Days;

import Helpers.InputHandler;
import Helpers.StringList;

import java.io.IOException;
import java.util.ArrayList;

public class Day6 {
    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_6/input.txt");
        System.out.println(totalGroupYessesOR(input) + ", " + totalGroupYessesAND(input));
    }

    private static int totalGroupYessesOR(ArrayList<String> input) {
        ArrayList<String> groups = StringList.collectLines(input, "");
        int count = 0;
        for(String g : groups) {
            count += StringList.countUnique(g);
        }
        return count;
    }

    private static int totalGroupYessesAND(ArrayList<String> input) {
        ArrayList<String> groups = StringList.collectLines(input, ";");
        int count = 0;
        for(String g : groups) {
            count += groupYessesAND(g);
        }
        return count;
    }

    private static int groupYessesAND(String g) {
        int groupYesses = 0;
        int groupSize = StringList.countOccurences(g, ';');
        ArrayList<Character> uniqueCharacters = StringList.uniqueCharacters(g);
        for (Character c : uniqueCharacters) {
            if (c != ';' && StringList.countOccurences(g, c) == groupSize) groupYesses++;
        }
        return groupYesses;
    }
}
