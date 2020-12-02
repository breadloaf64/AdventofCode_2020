package Helpers;

import java.util.ArrayList;

public class MultiDimIndexDistinct extends MultiDimIndex {

    public MultiDimIndexDistinct(int dimension, int maxIndex) {
        super(dimension, maxIndex);
        this.initialise();
    }

    private void initialise() {
        for (int j = 0; j < LENGTH; j++) {
            i[j] = j;
        }
    }

    public void iterateForward() {
        // IMPLEMENTATION IS REAL HARD ;_;
        super.iterateForward();
        while (!isValid()) {
            super.iterateForward();
        }
    }

    private boolean isValid() {
        return !hasDuplicate() & isAscending();
    }

    private boolean isAscending() {
        boolean isAscending = true;
        for(int j = 1; j < LENGTH; j++) {
            if (i[j] <= i[j - 1]) isAscending = false;
        }
        return isAscending;
    }

    public boolean hasDuplicate() {
        ArrayList<Integer> seenDigits = new ArrayList();
        for (int j = 0; j < LENGTH; j++) {
            if(seenDigits.contains(i[j])) return true;
            else seenDigits.add(i[j]);
        }
        return false;
    }

    public boolean isMin() {
        boolean isMin = true;
        for(int j = 0; j < LENGTH - 1; j++) {
            if(j == 0 & i[j] != 0) {
                isMin = false;
            }
            if (j > 0 & i[j] != i[j - 1] + 1) {
                isMin = false;
            }
        }
        return isMin;
    }

    public boolean isMax() {
        if(i[LENGTH - 1] != MAXINDEX) return false;
        for(int j = LENGTH - 2; j > 0; j--) {
            if (!(i[j] < i[j + 1])) {
                return false;
            }
        }
        return true;
    }
}
