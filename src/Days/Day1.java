package Days;

import Helpers.IntList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day1 {
    public static void solve() throws IOException {
        repairReport();
    }

    static void repairReport() throws IOException { //Day one
        String fileName = "src/Data/Day_1/input.txt";
        Path path = Paths.get(fileName);
        final Charset ENCODING = StandardCharsets.UTF_8;
        IntList numbers = new IntList((ArrayList<String>) Files.readAllLines(path, ENCODING));
        long solution1 = numbers.getNumbersThatAddTo(2, 2020).product();
        long solution2 = numbers.getNumbersThatAddTo(3, 2020).product();

        System.out.println("Solutions: " + solution1 + ", " + solution2);
    }
}
