package Helpers;

import java.util.ArrayList;
import java.util.Objects;

public class BootRunner {
    int pc; //program counter (index of next instruction)
    int acc; // accumulator
    ArrayList<String> program;
    boolean[] lineVisited;
    boolean hasLooped;
    boolean terminated;

    public BootRunner(ArrayList<String> program) {
        this.program = program;
        pc = 0;
        lineVisited = new boolean[program.size()];
        hasLooped = false;
    }

    public void iterate() {
        lineVisited[pc] = true;
        String instruction = program.get(pc);
        String op = instruction.split(" ")[0];
        int arg = Integer.parseInt(instruction.split(" ")[1]);
        if(Objects.equals(op, "nop")) {
            pc++;
        }
        else if (Objects.equals(op, "jmp")) {
            pc += arg;
        }
        else if (Objects.equals(op, "acc")) {
            acc += arg;
            pc++;
        }
        if(pc >= program.size()) terminated = true;
        if (!terminated && lineVisited[pc]) hasLooped = true;
    }

    public int acc() {return acc;}
    public boolean hasLooped() {return hasLooped;}
    public int pc() {return pc;}
    public boolean terminated() {return terminated;}
    public void printState() {
        System.out.println("=====================");
        System.out.println("PC: " + pc);
        System.out.println("ACC: " + acc);
        if (pc < program.size()) System.out.println("nextInstruction: " + program.get(pc));
        else System.out.println("END OF PROGRAM");
        System.out.println("");
    }

}
