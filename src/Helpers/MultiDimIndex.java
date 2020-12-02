package Helpers;

import java.util.ArrayList;


//REFACTOR THIS INTO AN INTERFACE AND SEPERATE IMPLEMENTATIONS FOR OVERLAP AND NO OVERLAP
public class MultiDimIndex {
    int[] i;
    final int DIMENSION;
    final boolean OVERLAP;
    final int MAXINDEX;

    public MultiDimIndex(int dimension, int maxIndex, boolean overlap) {
        this.DIMENSION = dimension;
        this.OVERLAP = overlap;
        this.MAXINDEX = maxIndex;
        i = new int[dimension];
        initialise();
    }

    public int getIndex(int j) {
        return i[j];
    }

    private void initialise() {
        for (int j = 0; j < DIMENSION; j++) {
            if (OVERLAP) {
                i[j] = 0;
            }
            else {
                i[j] = j;
            }
        }
    }

    public void print() {
        String out = "[";
        for(int j = 0; j < DIMENSION; j++) {
            if(j > 0) {
                out += ", ";
            }
            out += i[j];
        }
        out += "]";
        System.out.println(out);
    }

    public void iterateForward() {
        if (OVERLAP) iterateForwardWithOverlap();
        else iterateForwardWithoutOverlap();
    }



    private void iterateForwardWithoutOverlap() {
        // IMPLEMENTATION IS REAL HARD ;_;
    }

    public boolean hasDuplicate() {
        boolean hasDuplicate = false;
        ArrayList<Integer> seenDigits = new ArrayList();
        for (int j = 0; j < DIMENSION; j++) {
            if(seenDigits.contains(i[j])) return true;
            else seenDigits.add(i[j]);
        }
        return false;
    }

    private void iterateForwardWithOverlap() {
        i[DIMENSION - 1]++;
        int j = DIMENSION - 1;
        while(j >= 0) {
            if(i[j] == MAXINDEX) {
                i[j] = 0;
                if(j > 0) {
                    i[j - 1]++;
                }
            }
            j--;
        }
    }

    public boolean isZero() {
        boolean isZero = true;
        for(int j = 0; j < DIMENSION; j++) {
            if(i[j] != 0) {
                isZero = false;
            }
        }
        return isZero;
    }

    public boolean isMax() {
        boolean isMax = true;
        for(int j = 0; j < DIMENSION; j++) {
            if(i[j] != MAXINDEX - 1) {
                isMax = false;
            }
        }
        return isMax;
    }

    public int length() {
        return DIMENSION;
    }
}
