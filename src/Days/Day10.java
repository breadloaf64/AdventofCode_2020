package Days;

import Helpers.InputHandler;
import Helpers.IntList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day10 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_10/test1.txt");
        //System.out.println(oneJDTimesThreeJD(input));
        test(input);
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

    private static IntList adapterSequence(ArrayList<String> input) {
        IntList adapters = new IntList(input);
        IntList sequence = new IntList();
        int currentJolts = 0;
        return sequence;
    }

    private static IntList validAdapters(int jolts, IntList available) {
        IntList valid = new IntList();
        for (Integer a : available) {
            if (jolts < a && a < jolts + 4) valid.add(a);
        }
        return valid;
    }

    private static long numberOfArrangements(ArrayList<String> input) {
        Long count = 0L;
        IntList adapters = new IntList(input);
        adapters.add(adapters.largest() + 3);
        return numberOfArrangementsBetween(adapters,0, adapters.largest());
    }

    private static long numberOfArrangementsBetween(IntList adapters, int start, int target) {
        IntList validNext = validAdapters(start, adapters);
        Long count = 0L;
        for (Integer next : validNext) {
            count += numberOfArrangementsBetween(adapters, next, target);
        }
        return count;
    }

    private static void test(ArrayList<String> input) {
        System.out.println(numberOfArrangements(input));
    }
}
