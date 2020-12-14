package Helpers;

import java.util.ArrayList;
import java.util.Hashtable;

public class DockRunner {
    Hashtable<Long, Long> memory;
    String mask;
    int pc;
    ArrayList<String> program;
    boolean terminated;

    public DockRunner(ArrayList<String> program) {
        this.program = program;
        memory = new Hashtable();
        terminated = false;
    }

    public void iterate() {

        String instruction = program.get(pc);

        this.processInstruction(instruction);
        pc++;
        if(pc >= program.size()) terminated = true;

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
        memory.put(address, maskValue(value));
    }

    public long maskValue(long value) {
        String strValue = Long.toBinaryString(value);
        strValue = zeroPadding(36 - strValue.length()) + strValue;
        StringBuilder newVal = new StringBuilder(strValue);
        for (int i = 0; i < 36; i++) {
            if (mask.charAt(i) == '0') {
                newVal.setCharAt(i, '0');
            }
            else if (mask.charAt(i) == '1') {
                newVal.setCharAt(i, '1');
            }
        }
        return Long.parseLong(newVal.toString(), 2);
    }

    String zeroPadding(int n) {
        String pad = "";
        for (int i = 0; i < n; i++) {
            pad += "0";
        }
        return pad;
    }

    public boolean terminated() {return terminated;}

    public long memorySum() {
        long sum = 0L;
        for (Long key : memory.keySet()) {
            sum += memory.get(key);
        }
        return sum;
    }
}
