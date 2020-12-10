package Helpers;

import java.util.ArrayList;
import java.util.Objects;

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

        while(!found && !i.hasLooped()) {
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

    public IntList differences() {
        IntList differences = new IntList();
        for (int i = 1; i < this.size(); i++) {
            differences.add(this.get(i) - this.get(i - 1));
        }
        return differences;
    }

    public void removeDuplicates() {
        IntList seen = new IntList();
        int i = 0;
        Integer n;
        while (i < this.size()) {
            n = this.get(i);
            if(seen.contains(n)) {
                this.remove(Integer.valueOf(n));
            }
            else {
                seen.add(n);
                i++;
            }
        }
    }

    public int largest() {
        int largest = this.get(0);
        for (Integer n : this) {
            if (n > largest) largest = n;
        }
        return largest;
    }

    public int smallest() {
        int smallest = this.get(0);
        for (Integer n : this) {
            if (n < smallest) smallest = n;
        }
        return smallest;
    }

    public int countOccurences(int x) {
        int count = 0;
        for (Integer n : this) {
            if (Objects.equals(x, n)) count++;
        }
        return count;
    }
}
