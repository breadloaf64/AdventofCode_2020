package Days;

import Helpers.InputHandler;
import Helpers.IntList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Day10 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_10/input.txt");
        System.out.println("Day 10: " + oneJDTimesThreeJD(input) + ", " + numberOfArrangements(input));
    }

    private static int oneJDTimesThreeJD(ArrayList<String> input) {
        IntList adapters = new IntList(input);
        Collections.sort(adapters);
        adapters.removeDuplicates();
        adapters.add(0, 0);
        adapters.add(adapters.largest() + 3);
        IntList differences = adapters.differences();
        return differences.countOccurences(1) * differences.countOccurences(3);
    }

    private static IntList validAdapters(int jolts, IntList available) {
        IntList valid = new IntList();
        for (Integer a : available) {
            if (jolts < a && a < jolts + 4) valid.add(a);
        }
        return valid;
    }

    static class recursiveFinder {
        private Hashtable<Integer, Long> known;
        private IntList adapters;

        recursiveFinder(IntList adapters) {
            this.adapters = adapters;
            this.adapters.add(adapters.largest() + 3);
            known = new Hashtable();
        }

        public long numberOfArrangements(int start, int depth) {
            //System.out.println(indents(depth) + "[start: " + start + "]");
            Long count = 0L;
            if (known.containsKey(start)) {
                count = known.get(start);
                //System.out.println(indents(depth) + "{count known: " + count + "}");
            }
            else {
                IntList validNext = validAdapters(start, adapters);

                for (Integer next : validNext) {
                    count += numberOfArrangements(next, depth + 1);
                }
                if (validNext.size() == 0) {
                    count = 1L;
                }
                known.put(start, count);
                //System.out.println(indents(depth) + "{counted: " + count + "}");
            }
            return count;
        }
    }

    private static long numberOfArrangements(ArrayList<String> input) {
        recursiveFinder r = new recursiveFinder(new IntList(input));
        return r.numberOfArrangements(0, 0);
    }

    private static String indents(int n) {
        String out = "";
        for (int i = 0; i < n; i++) {
            out += "|  ";
        }
        return out;
    }

}
