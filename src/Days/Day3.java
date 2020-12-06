package Days;

import Helpers.InputHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Day3 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_3/input.txt");
        countTrees(input);
    }

    static void countTrees(ArrayList<String> input) throws IOException { //Day 3
        ArrayList<String> map = input;
        long product = 1;
        product *= treeCount(map, 1, 1);
        product *= treeCount(map, 1, 3);
        product *= treeCount(map, 1, 5);
        product *= treeCount(map, 1, 7);
        product *= treeCount(map, 2, 1);
        System.out.println("Day 3: " + treeCount(map, 1, 3) + ", " + product);
    }

    static int treeCount(ArrayList<String> map, int di, int dj) {
        int width = map.get(0).length(), treeCount = 0, j = 0;
        for (int i = 0; i < map.size(); i += di) {
            if(map.get(i).charAt(j) == '#') treeCount++;
            j = (j + dj) % width;
        }
        return treeCount;
    }

}
