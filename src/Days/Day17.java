package Days;

import Helpers.*;

import java.io.IOException;
import java.util.ArrayList;

public class Day17 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_17/input.txt");
        System.out.println(part1better(input));
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

    private static int part1better(ArrayList<String> input) {
        ConwayAutomaton c = new ConwayAutomaton(input, 4, true);
        //c.draw();
        for(int i = 0 ; i < 6; i++) {
            //System.out.println("After cycle " + (i + 1) + ":");
            c = c.iterate();
            //c.draw();
            //System.out.println();
        }
        return c.count();
    }


    private static void test(ArrayList<String> input) {
        MultiDimIndex i = new MultiDimIndex(3, 3);
        while(!i.hasLooped()) {
            i.print();
            i.iterateForward();
        }
    }
}
