package Days;

import Helpers.InputHandler;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Day5 {
    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_5/input.txt");
        System.out.println("Day 5: " + highestPass(input) + ", " + remainingPass(input));
    }

    private static int remainingPass(ArrayList<String> passes) {
        ArrayList<Integer> allPasses = new ArrayList();
        for (String s : passes) {
            allPasses.add(seatID(s));
        }
        ArrayList<Integer> possiblePasses = new ArrayList();
        for(int i = 0; i < 818; i++) {
            if(allPasses.contains(i - 1) && allPasses.contains(i + 1)) {
                possiblePasses.add(i);
            }
        }
        for(Integer pass : allPasses) {
            possiblePasses.remove(pass);
        }
        return possiblePasses.get(0);
    }

    private static int highestPass(ArrayList<String> passes) {
        int highest = 0;
        for(String s : passes) {
            int ID = seatID(s);
            if(ID > highest) highest = ID;
        }
        return highest;
    }

    private static int seatID(String seat) {
        int row = 0, col = 0;
        for(int i = 0; i < seat.length(); i++) {
            if (seat.charAt(i) == 'B') row += Math.pow(2, 6 - i);
            else if (seat.charAt(i) == 'R') col += Math.pow(2, 6 - i + 3);
        }
        return row * 8 + col;
    }
}
