package Helpers;


//REFACTOR THIS INTO AN INTERFACE AND SEPERATE IMPLEMENTATIONS FOR OVERLAP AND NO OVERLAP
public class MultiDimIndex {
    int[] i;
    final int LENGTH;
    final int MAXINDEX;

    public MultiDimIndex(int length, int maxIndex) {
        this.LENGTH = length;
        this.MAXINDEX = maxIndex;
        i = new int[LENGTH];
        initialise();
    }

    public int getIndex(int j) {
        return i[j];
    }

    private void initialise() {
        for (int j = 0; j < LENGTH; j++) {
            i[j] = 0;
        }
    }

    public void print() {
        String out = "[";
        for(int j = 0; j < LENGTH; j++) {
            if(j > 0) {
                out += ", ";
            }
            out += i[j];
        }
        out += "]";
        System.out.println(out);
    }

    public void iterateForward() {
        i[LENGTH - 1]++;
        int j = LENGTH - 1;
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

    public boolean isMin() {
        boolean isMin = true;
        for(int j = 0; j < LENGTH; j++) {
            if(i[j] != 0) {
                isMin = false;
            }
        }
        return isMin;
    }

    public boolean isMax() {
        boolean isMax = true;
        for(int j = 0; j < LENGTH; j++) {
            if(i[j] != MAXINDEX - 1) {
                isMax = false;
            }
        }
        return isMax;
    }

    public int length() { return LENGTH; }
}
