package Days;

import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Day15 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_15/input.txt");
        System.out.println("Day 15: " + vanEck(input.get(0), 2020) + ", " + vanEck(input.get(0), 30000000));
    }


    private static ArrayList<Integer> generateGame(String s, int turn) {
        ArrayList<Integer> game = new ArrayList();
        String[] start = s.split(",");
        for (int j = 0; j < start.length; j++) {
            game.add(Integer.parseInt(start[j]));
        }
        int last;

        while(game.size() < turn) {
            last = game.get(game.size() - 1);
            game.add(turn(game, last));
            System.out.println(game.size());
        }
        return game;
    }

    private static int turn(ArrayList<Integer> game, int last) {
        for (int i = game.size() - 2; i >= 0; i--) {
            if(game.get(i) == last) return game.size() - 1 - i;
        }
        return 0;
    }

    private static int vanEck(String s, int n) {
        Hashtable<Integer, Integer> lastOccurence = new Hashtable();
        String[] start = s.split(",");
        for (int j = 0; j < start.length - 1; j++) {
            lastOccurence.put(Integer.parseInt(start[j]), j + 1);
        }
        int turn = start.length + 1;
        int previous = Integer.parseInt(start[turn - 2]);
        int next = 0;
        while (turn != n + 1) {
            if (lastOccurence.keySet().contains(previous)) next = turn - lastOccurence.get(previous) - 1;
            else next = 0;
            lastOccurence.put(previous, turn - 1);
            previous = next;
            turn++;
        }
        return next;
    }
}
