package Days;

import Helpers.Password;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day2 {
    public static void solve() throws IOException {
        validatePasswords();
    }

    static void validatePasswords() throws  IOException { //Day 2
        final Charset ENCODING = StandardCharsets.UTF_8;
        String fileName = "src/Data/Day_2/input.txt";
        Path path = Paths.get(fileName);
        ArrayList<String> policyPasswords = (ArrayList<String>) Files.readAllLines(path, ENCODING);

        int count0 = 0;
        int count1 = 0;
        for (String p : policyPasswords) {
            if ((new Password(p).isValid(0))) {
                count0++;
            }
            if ((new Password(p).isValid(1))) {
                count1++;
            }
        }
        System.out.println(count0 + ", " + count1);
    }
}
