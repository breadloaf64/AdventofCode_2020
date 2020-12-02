package Helpers;

import java.util.ArrayList;

public class IntList extends ArrayList<Integer>{
    public IntList() {
        super();
    }

    public IntList(ArrayList<String> strings) {
        super();
        for(String s : strings) {
            this.add(Integer.parseInt(s));
        }
    }

    public void print() {
        for(Integer i : this) {
            System.out.println(i);
        }
    }

    public long sum() {
        long sum = 0;
        for(Integer i : this) {
            sum += i;
        }
        return sum;
    }

    public long sum(MultiDimIndex is) {
        int sum = 0;
        for(int i = 0; i < is.length(); i++) {
            sum += this.get(is.getIndex(i));
        }
        return sum;
    }

    public IntList getNumbersThatAddTo(int n, int target) {
        boolean found = false;

        MultiDimIndexDistinct i = new MultiDimIndexDistinct(n, this.size());
        IntList numbers = new IntList();

        while(!(found | i.isMax())) {
            if(this.sum(i) == target) {
                for(int j = 0; j < i.length(); j++) {
                    numbers.add(this.get(i.getIndex(j)));
                    found = true;
                }
            }
            i.iterateForward();
        }
        return numbers;
    }

    public long product() {
        long product = 1;
        for(Integer i : this) {
            product *= i;
        }
        return product;
    }
}
