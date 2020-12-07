package Days;

import Helpers.InputHandler;
import Helpers.Printer;
import Helpers.StringList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day7 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_7/input.txt");
        System.out.println(countValidOuterBag(input, "shiny gold bag"));
        //test(input);
    }

    private static int countValidOuterBag(ArrayList<String> rules, String myBag) {
        int oldCount = -1;
        int newCount = 0;
        ArrayList<String> validOuterBags = addLayer(rules, myBag);
        while (oldCount != newCount) {
            oldCount = validOuterBags.size();
            validOuterBags = addLayer(rules, validOuterBags);
            newCount = validOuterBags.size();
        }
        return newCount;
    }

    private static ArrayList<String> addLayer(ArrayList<String> rules, ArrayList<String> validOuterBags) {
        ArrayList<String> newValidOuterBags = new ArrayList(validOuterBags);
        String ruleOuterBag;
        ArrayList<String> ruleInnerBags;
        for (String rule : rules) {
            ruleOuterBag = ruleOuterBag(rule);
            ruleInnerBags = ruleInnerBags(rule);
            for (String bag : validOuterBags) {
                if (ruleInnerBags.contains(bag)) {
                    if (!newValidOuterBags.contains(ruleOuterBag)) newValidOuterBags.add(ruleOuterBag);
                }
            }
        }
        return newValidOuterBags;
    }

    private static ArrayList<String> addLayer(ArrayList<String> rules, String firstBag) {
        ArrayList<String> newValidOuterBags = new ArrayList();
        String ruleOuterBag;
        ArrayList<String> ruleInnerBags;
        for (String rule : rules) {
            ruleOuterBag = ruleOuterBag(rule);
            ruleInnerBags = ruleInnerBags(rule);
            if (ruleInnerBags.contains(firstBag)) {
                if (!newValidOuterBags.contains(ruleOuterBag)) newValidOuterBags.add(ruleOuterBag);
            }
        }
        return newValidOuterBags;
    }

    private static String ruleOuterBag(String rule) {
        String outerBag = rule.split("s contain ")[0];
        return outerBag;
    }

    private static ArrayList<String> ruleInnerBags(String rule) {
        ArrayList<String> innerBags = new ArrayList();
        String inner = rule.split("s contain ")[1];
        Collections.addAll(innerBags, inner.split(", "));
        for (int i = 0; i < innerBags.size(); i++) {
            innerBags.set(i, strip(innerBags.get(i)));
        }
        return innerBags;
    }

    private static String strip(String bag) {

        String strip = bag.replaceAll("\\d+ +", ""); //remove leading number and space

        strip = StringList.removeLastCharacter(strip, '.');
        strip = StringList.removeLastCharacter(strip, 's');
        return strip;
    }

    private static void test(ArrayList<String> input) {

    }

}
