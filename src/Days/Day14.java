package Days;

import Helpers.DockRunner;
import Helpers.DockRunner2;
import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Day14 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_14/input.txt");
        System.out.println("Day 14: " + part1(input) + ", " + part2(input));
    }

    public static long part1(ArrayList<String> input) {
        DockRunner r = new DockRunner(input);
        while(!r.terminated()) {
            r.iterate();
        }
        return r.memorySum();
    }

    public static long part2(ArrayList<String> input) {
        DockRunner2 r = new DockRunner2(input);
        while(!r.terminated()) {
            r.iterate();
        }
        return r.memorySum();
    }
}
