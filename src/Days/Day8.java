package Days;

import Helpers.BootRunner;
import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Day8 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_8/input.txt");
        ArrayList<String> input2 = InputHandler.get("src/Data/Day_8/inputFixed.txt");
        System.out.println("Day 8: " + getAccBeforeLoop(input) + " " + getAccBeforeTerminate(input2));
        //System.out.println(findTerminatingProgram(input));
    }

    private static int findTerminatingProgram(ArrayList<String> input) {
        //outputs the toggled line.
        for (int i = 0; i < input.size(); i++) {
            if (terminates(toggleAt(input, i))) {
                return i;
            }
        }
        return 0;
    }

    private static ArrayList<String> toggleAt(ArrayList<String> input, int i) {
        /*outputs the instructions, but with jmp swapped with nop on line i,
        or the same if line i has a different instruction. */
        ArrayList<String> toggled = new ArrayList();
        for(int j = 0; j < input.size(); j++) {
            if (j == i) toggled.add(toggle(input.get(j)));
            else toggled.add(input.get(j));
        }
        return toggled;
    }

    private static String toggle(String instruction) {
        String op = instruction.split(" ")[0];
        String arg = instruction.split(" ")[1];
        String newOp;
        if (Objects.equals(op, "nop")) newOp = "jmp";
        else if (Objects.equals(op, "jmp")) newOp = "nop";
        else newOp = op;
        return newOp + " " + arg;
    }

    private static int getAccBeforeLoop(ArrayList<String> input) {
        BootRunner r = new BootRunner(input);
        while(!r.hasLooped()) {
            r.iterate();
        }
        return r.acc();
    }

    private static boolean terminates(ArrayList<String> input) {
        BootRunner r = new BootRunner(input);
        while(!(r.terminated() || r.hasLooped())) {
            r.iterate();
        }
        return r.terminated();
    }

    private static int getAccBeforeTerminate(ArrayList<String> input) {
        BootRunner r = new BootRunner(input);
        while(!(r.terminated() || r.hasLooped())) {
            r.iterate();
        }
        return r.acc();
    }
}
