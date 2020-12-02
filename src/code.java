import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Helpers.*;

public class code {
    final static Charset ENCODING = StandardCharsets.UTF_8;

    public static void main(String[] args) throws IOException {
        //reportRepair();
        validatePasswords();
        //test();
    }

    static void test() {
        Password p = new Password("1-3 b: cdefg");
        System.out.println(p.isValid(1));
    }

    static void validatePasswords() throws  IOException { //Day two
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

    static void reportRepair() throws IOException { //Day one
        String fileName = "src/Data/Day_1/input.txt";
        Path path = Paths.get(fileName);

        IntList numbers = new IntList((ArrayList<String>) Files.readAllLines(path, ENCODING));
        long solution1 = numbers.getNumbersThatAddTo(2, 2020).product();
        long solution2 = numbers.getNumbersThatAddTo(3, 2020).product();

        System.out.println("Solutions: " + solution1 + ", " + solution2);
    }


    static void printArrayListString(ArrayList<String> strings) {
        for (String s : strings) {
            System.out.println(s);
        }
    }
}


