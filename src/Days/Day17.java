package Days;

import Helpers.*;

import java.io.IOException;
import java.util.ArrayList;

public class Day17 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_17/input.txt");
        System.out.println("Day 17: " + sixthIteration(input, 3) + ", " + sixthIteration(input, 4));
    }

    private static int sixthIteration(ArrayList<String> input, int dimension) {
        ConwayAutomaton c = new ConwayAutomaton(input, dimension, true);
        for(int i = 0 ; i < 6; i++) {
            c = c.iterate();
            //System.out.println(i);
        }
        return c.count();
    }
}
