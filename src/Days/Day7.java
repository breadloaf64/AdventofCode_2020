package Days;

import Helpers.InputHandler;
import Helpers.Printer;
import Helpers.StringList;
import Helpers.Tuple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

public class Day7 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_7/input.txt");
        //System.out.println(countValidOuterBag(input, "shiny gold bag"));
        System.out.println(countTotalInnerBags(input, "shiny gold bag"));
        //test(input);
        //ruleInnerBagsCount("dark violet bags contain no other bags.");
        //System.out.println("");
    }

    private static int countTotalInnerBags(ArrayList<String> rules, String myBag) {
        int count = -1;
        LinkedList<String> bags = new LinkedList();
        bags.add(myBag);
        while(bags.size() > 0) {
            count++;
            String current = bags.remove();
            for(String rule : rules) {
                if (Objects.equals(ruleOuterBag(rule), current)) {
                    ArrayList<Tuple<String, Integer>> innerBagsCount = ruleInnerBagsCount(rule);
                    for (Tuple<String, Integer> bagCount : innerBagsCount) {
                        for (int i = 0; i < bagCount.y; i++) {
                            bags.add(bagCount.x);
                        }
                    }
                }
            }
        }
        return count;
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

    private static ArrayList<Tuple<String, Integer>> ruleInnerBagsCount(String rule) {
        ArrayList<Tuple<String, Integer>> innerBagsCount = new ArrayList();
        ArrayList<String> innerBags = new ArrayList();
        String inner = rule.split("s contain ")[1];
        Collections.addAll(innerBags, inner.split(", "));

        String innerBag;
        Integer count;
        String strCount;

        for (int i = 0; i < innerBags.size(); i++) {
            innerBag = strip(innerBags.get(i));
            strCount = innerBags.get(i).split(" ")[0];
            if (Objects.equals(strCount, "no")) count = 0;
            else count = Integer.parseInt(strCount);
            innerBagsCount.add(new Tuple<String, Integer>(innerBag, count));
        }
        return innerBagsCount;
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
