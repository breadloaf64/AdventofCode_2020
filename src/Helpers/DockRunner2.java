package Helpers;

import java.util.ArrayList;
import java.util.Hashtable;

public class DockRunner2 extends DockRunner {
    public DockRunner2(ArrayList<String> program) {
        super(program);
    }

    public void processInstruction(String instruction) {
        if(instruction.charAt(1) == 'e') { //memory instruction
            writeToMemory(instruction);
        }
        else { //mask instruction
            mask = instruction.split(" ")[2];
        }
    }

    private void writeToMemory(String instruction) {
        long address = Long.parseLong(instruction.split("\\[|\\]")[1]);
        long value = Long.parseLong(instruction.split(" ")[2]);
        ArrayList<Long> addresses = addresses(address);
        for (Long a : addresses) {
            memory.put(a, value);
        }
    }

    private ArrayList<Long> addresses(long address) {
        String strAddress = Long.toBinaryString(address);
        strAddress = zeroPadding(36 - strAddress.length()) + strAddress;
        ArrayList<Long> addresses = new ArrayList();

        StringBuilder a = new StringBuilder(strAddress);
        for (int i = 0; i < 36; i++) {
            if (mask.charAt(i) == 'X') {
                a.setCharAt(i, 'X');
            } else if (mask.charAt(i) == '1') {
                a.setCharAt(i, '1');
            }
        }
        int xCount = StringList.countOccurences(a.toString(), 'X');

        for (int i = 0; i < Math.pow(2, xCount); i++) {
            addresses.add(replaceX(a.toString(), i, xCount));
        }
        return addresses;
    }

    private Long replaceX (String a, int n, int xCount) {
        String strReplace = Integer.toBinaryString(n);
        strReplace = zeroPadding(xCount - strReplace.length()) + strReplace;
        int j = 0;
        StringBuilder s = new StringBuilder(a);
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == 'X') {
                s.setCharAt(i, strReplace.charAt(j));
                j++;
            }
        }
        //System.out.println("a: " + a + ", n: " + n + ", s: " + s.toString());
        return Long.parseLong(s.toString(), 2);
    }
}