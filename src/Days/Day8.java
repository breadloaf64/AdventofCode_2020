package Days;

import Helpers.BootRunner;
import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Day8 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_8/input.txt");
        System.out.println(getAccBeforeLoop(input));
        //test(input)
    }

    private static int getAccBeforeLoop(ArrayList<String> input) {
        BootRunner r = new BootRunner(input);
        r.printState();
        while(!r.hasLooped()) {

            r.iterate();
            r.printState();
        }
        return r.acc();
    }

    private static void test(ArrayList<String> input) {

    }
}
