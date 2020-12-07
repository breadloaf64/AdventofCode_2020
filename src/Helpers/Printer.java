package Helpers;

import java.util.ArrayList;

public class Printer {
    public static void print(ArrayList<String> strings) {
        for (String s : strings) {
            System.out.println("'" + s + "'");
        }
    }
}
