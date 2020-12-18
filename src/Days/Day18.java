package Days;

import Helpers.InputHandler;
import Helpers.StringList;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Day18 {

    public static void solve() throws IOException {
        ArrayList<String> input = InputHandler.get("src/Data/Day_18/input.txt");
        Long unprecidented = 0L;
        Long precidented = 0L;
        for (String line : input) {
            Expression e1 = new Expression(line, false);
            Expression e2 = new Expression(line, true);
            unprecidented += e1.evaluate();
            precidented += e2.evaluate();
        }
        System.out.println("Day 18: " + unprecidented + ", " + precidented);
        //test(input);
    }

    private static void test(ArrayList<String> input) {
        Expression e = new Expression("2 + (5 * 3) + 2 * 4", true);
        System.out.println(e.findRPN(e.infix));
        System.out.println(e.evaluateRPN(e.findRPN(e.infix)));
    }

    static class Expression {
        String in;
        String infix; //infix
        boolean precidence;

        Expression(String input, boolean precidence) {
            String expr = "";
            char current;
            for (int i = 0; i < input.length(); i++) {
                current = input.charAt(i);
                if (current != ' ') {
                    expr += current;
                }
            }
            infix = expr;
            this.precidence = precidence;
        }

        String switchParentheses(String s) {
            String out = "";
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c == ')') {
                    out += '(';
                }
                else if (c == '(') {
                    out += ')';
                }
                else {
                    out += c;
                }
            }
            return out;
        }

        String findRPN(String in) {
            String inreverse = switchParentheses(StringList.reverse(in));
            char token;
            String out = "";
            Stack<Character> opStack = new Stack<>();

            for (int i = 0; i < in.length(); i++) {
                token = inreverse.charAt(i);

                if ('0' <= token && token <= '9') {
                    out += token;
                }
                else if (token == '+' | token == '*') {
                    while (!opStack.isEmpty() && hasGreaterPrecidence(opStack.peek(), token) && opStack.peek() != '(') {
                        out += opStack.pop();
                    }
                    opStack.push(token);
                }
                else if (token == '(') {
                    opStack.push(token);
                }
                else if (token == ')') {
                    while (opStack.peek() != '(') {
                        out += opStack.pop();
                    }
                    opStack.pop();
                }
            }
            while (!opStack.isEmpty()) {
                out += opStack.pop();
            }
            return out;
        }

        Long evaluateRPN(String rpn) {
            Stack<Long> values = new Stack<>();
            char token;
            for (int i = 0; i < rpn.length(); i++) {
                token = rpn.charAt(i);
                if ('0' <= token && token <= '9') {
                    values.push(Long.parseLong(Character.toString(token)));
                }
                else if (token == '+') {
                    values.push(values.pop() + values.pop());
                }
                else values.push(values.pop() * values.pop());
            }
            return values.pop();
        }

        boolean hasGreaterPrecidence(char x, char y) { //return true if x has greater precidence than y
            if (!precidence) return false;
            if (x == '+' && y == '*') {
                return true;
            }
            else return false;
        }

        Long evaluate() {
            return evaluateRPN(findRPN(infix));
        }


    }
}
