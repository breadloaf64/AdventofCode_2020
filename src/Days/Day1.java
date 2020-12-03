package Days;

import Helpers.InputHandler;
import Helpers.IntList;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day1 {
    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_1/input.txt");
        repairReport(input);
    }

    static void repairReport(ArrayList<String> input) throws IOException { //Day one
        IntList numbers = new IntList(input);
        long solution1 = numbers.getNumbersThatAddTo(2, 2020).product();
        long solution2 = numbers.getNumbersThatAddTo(3, 2020).product();
        System.out.println("Solutions: " + solution1 + ", " + solution2);
    }
}
