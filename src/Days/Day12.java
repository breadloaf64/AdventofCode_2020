package Days;

import Helpers.GridWalker;
import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Day12 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_12/test.txt");
        System.out.println(part2(input));
        //test(input);
    }

    private static double part1(ArrayList<String> input) {
        GridWalker g = new GridWalker(0, 0, 0);
        for (String instruction : input) {
            g.runCommand(instruction);
        }
        return g.manhattanDistance();
    }

    private static double part2(ArrayList<String> input) {
        GridWalker ship = new GridWalker(0, 0, 0);
        GridWalker waypoint = new GridWalker(10, 1, 0);
        double inter;
        for (String instruction : input) {
            String op = instruction.substring(0, 1);
            int arg = Integer.parseInt(instruction.substring(1));
            if (Objects.equals(op, "F")) {
                for (int i = 0; i < arg; i++) {
                    ship.runCommand("E" + Math.round(waypoint.x));
                    ship.runCommand("N" + (-Math.round(waypoint.y)));
                }
            }
            else if (Objects.equals(op, "L") | Objects.equals(op, "R")) {
                waypoint.rotateAboutOrigin(op, arg);
            }
            else waypoint.runCommand(instruction);
        }
        return ship.manhattanDistance();
    }


    private static void test(ArrayList<String> input) {

    }
}
