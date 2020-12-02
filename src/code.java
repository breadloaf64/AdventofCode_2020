import java.io.IOException;
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
        reportRepair();
        //test();
    }

    static void test() {
        MultiDimIndex i = new MultiDimIndex(1, 200, true);
        while(!i.isMax()) {
            i.print();
            i.iterateForward();
        }
    }

    static void reportRepair() throws IOException { //Day one
        String fileName = "src/Data/Day_1/input.txt";
        Path path = Paths.get(fileName);

        IntList numbers = new IntList((ArrayList<String>) Files.readAllLines(path, ENCODING));
        long solution1 = numbers.getNumbersThatAddTo(2, 2020).product();
        long solution2 = numbers.getNumbersThatAddTo(1, 2020).product();

        System.out.println("Solutions: " + solution1 + ", " + solution2);
    }


    static void printArrayListString(ArrayList<String> strings) {
        for (String s : strings) {
            System.out.println(s);
        }
    }
}


