import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Helpers.*;

import javax.swing.event.MouseInputListener;

public class code {
    public static void main(String[] args) throws IOException {
        reportRepair();
    }

    static void testMultiDimIndex() {
        MultiDimIndex i = new MultiDimIndex(3, 3, false);
        i.print();
        i.iterateForward();
        int j = 0;
        while(j < 10) {
            i.print();
            i.iterateForward();
            j++;
        }
    }


    static void reportRepair() throws IOException { //Day one
        String fileName = "src/Data/Day_1/input.txt";
        Path path = Paths.get(fileName);
        Charset ENCODING = StandardCharsets.UTF_8;
        ArrayList<Integer> numbers = convertArrayListOfStringtoInt((ArrayList<String>) Files.readAllLines(path, ENCODING));
        //printArrayListInteger(numbers);
        ArrayList<Integer> midSol1 = getNumbersThatAddTo(numbers, 2, 2020);
        long solution1 = productOfIntegersInArrayList(midSol1);
        ArrayList<Integer> midSol2 = getNumbersThatAddTo(numbers, 3, 2020);
        long solution2 = productOfIntegersInArrayList(midSol2);
        System.out.println(solution2);
    }


    static ArrayList<Integer> convertArrayListOfStringtoInt(ArrayList<String> strings) {
        ArrayList<Integer> integers = new ArrayList();
        for (String s : strings) {
            integers.add(Integer.parseInt(s));
        }
        return integers;
    }

    static ArrayList<Integer> getNumbersThatAddTo(ArrayList<Integer> list, int n, int target) {
        boolean found = false;
        final int MAXINDEX = list.size();

        MultiDimIndex i = new MultiDimIndex(n, MAXINDEX, true);
        ArrayList<Integer> numbers = new ArrayList();

        while(!found) {
            if(sumOfIntegersInListByIndex(list, i) == target) {
                for(int j = 0; j < i.length(); j++) {
                    numbers.add(list.get(i.getIndex(j)));
                    found = true;
                }
            }
            i.iterateForward();
            i.print();
        }

        return numbers;
    }

    static int sumOfIntegersInListByIndex(ArrayList<Integer> list, MultiDimIndex is) {
        int result = 0;
        for(int i = 0; i < is.length(); i++) {
            result += list.get(is.getIndex(i));
        }
        return result;
    }

    static long productOfIntegersInArrayList(ArrayList<Integer> list) {
        int result = 1;
        for (Integer n : list) {
            result *= n;
        }
        return result;
    }

    static int sumOfIntegersInArrayList(ArrayList<Integer> list) {
        int result = 0;
        for (Integer n : list) {
            result += n;
        }
        return result;
    }


    static void printArrayListString(ArrayList<String> strings) {
        for (String s : strings) {
            System.out.println(s);
        }
    }

    static void printArrayListInteger(ArrayList<Integer> integers) {
        for (Integer i : integers) {
            System.out.println(i.toString());
        }
    }
}


