package Days;

import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Day13 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_13/input.txt");
        //System.out.println(part2(input));
        System.out.println(findInArithmeticProgression(12, 3L, 12L));
    }

    private static void test(ArrayList<String> input) {

    }

    private static long part2(ArrayList<String> input) {
        String[] b = input.get(1).split(",");

        //create list of mod restrictions, represented as tuples x = 0 (mod 3) represented as (0, 3)
        //sort tuples in decreasing order of mod value
        //enforce the remainder is less than the mod value. So x = 5 (mod 3) becomes x = 2 (mod 3)
        //          we get 2 since 2 = 5 % 3



        return 0L;
    }

    private static long findInArithmeticProgression(int f, Long N, long nextN) {
        int i = 1;
        while(true) {
            if ((f + N * i) % nextN == 0) {
                return f + N * i;
            }
            i++;
        }
    }


    private static boolean divides(int x, long y) {
        return y % x == 0;
    }

    private static int part1(ArrayList<String> input) {
        int waitStart = Integer.parseInt(input.get(0));
        ArrayList<Integer> busses = busses(input.get(1));
        int earliestBus = busses.get(0);
        int shortestWait = waitTime(waitStart, earliestBus);
        int wait = 0;
        for (Integer bus : busses) {
            wait = waitTime(waitStart, bus);
            if (wait < shortestWait) {
                earliestBus = bus;
                shortestWait = wait;
            }
        }
        return earliestBus * shortestWait;
    }

    private static int waitTime(int waitStart, int bus) {
        //return bus * (924 / bus + 1) - waitStart;
        return bus - waitStart % bus;
    }

    private static ArrayList<Integer> busses(String list) {
        ArrayList<Integer> busses = new ArrayList();
        String[] b = list.split(",");
        for (int i = 0; i < b.length; i++) {
            if(!Objects.equals("x", b[i])) {
                busses.add(Integer.parseInt(b[i]));
            }
        }
        return busses;
    }

}
