package Days;

import Helpers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day9 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_9/test.txt");
        System.out.println(firstException(input, 5));
        //test(input);
    }

    public static Long firstException(ArrayList<String> input, int preambleLength) {
        LongList data = StringList.toLongList(input);
        LongList preamble = new LongList();
        LongList summands;
        int i = 0;
        for(Long n : data) {
            System.out.println(n);
            if(i < preambleLength) {
                preamble.add(n);
            }
            else {
                summands = preamble.getNumbersThatAddTo(2, n);
                if (summands.size() == 2 && summands.sum() == n) {
                    preamble.add(n);
                    preamble.remove(0);
                }
                else return n;
            }
            i++;
        }
        return 0L;
    }

    private static void test(ArrayList<String> input) {
        MultiDimIndexDistinct i = new MultiDimIndexDistinct(2, 5);
        while(!i.isMin()) {

            i.print();
            i.iterateForward();
        }
    }
}
