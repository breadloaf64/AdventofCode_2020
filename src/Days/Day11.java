package Days;

import Helpers.FerryAutomaton;
import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Day11 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_11/input.txt");
        System.out.println("Day 11: " + stableOccupied(input) + ", " + stableOccupiedVision(input));
    }

    private static int stableOccupied(ArrayList<String> input) {
        FerryAutomaton f = new FerryAutomaton(input);
        //f.print();

        while (f.hasChanged()) {
            f = f.next();
            //System.out.println();
            //f.print();
        }
        return f.countOccupied();
    }

    private  static int stableOccupiedVision(ArrayList<String> input) {
        FerryAutomaton f = new FerryAutomaton(input);
        //f.print();

        while (f.hasChanged()) {
            f = f.nextVision();
            //System.out.println();
            //f.print();
        }
        return f.countOccupied();
    }
}
