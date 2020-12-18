package Days;

import Helpers.ConwayCubes;
import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Day17 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_17/input.txt");
        System.out.println(part1(input));
        //test(input);
    }

    private static int part1(ArrayList<String> input) {
        ConwayCubes c = new ConwayCubes(input, 0);
        c.draw();
        for(int i = 0 ; i < 6; i++) {
            System.out.println("After cycle " + (i + 1) + ":");
            c = c.iterate();
            c.draw();
            System.out.println();
        }
        return c.count();
    }

    private static void test(ArrayList<String> input) {

    }
}
