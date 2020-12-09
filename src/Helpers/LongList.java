package Helpers;

import java.util.ArrayList;

public class LongList extends ArrayList<Long>{
    public LongList() {
        super();
    }

    public LongList(ArrayList<String> strings) {
        super();
        for(String s : strings) {
            this.add(Long.parseLong(s));
        }
    }

    public void print() {
        for(Long i : this) {
            System.out.println(i);
        }
    }

    public long sum() {
        long sum = 0;
        for(Long i : this) {
            sum += i;
        }
        return sum;
    }

    public long sum(MultiDimIndex is) {
        Long sum = 0L;
        for(int i = 0; i < is.length(); i++) {
            sum += this.get(is.getIndex(i));
        }
        return sum;
    }

    public LongList getNumbersThatAddTo(int n, Long target) {
        boolean found = false;

        MultiDimIndexDistinct i = new MultiDimIndexDistinct(n, this.size());
        LongList numbers = new LongList();

        while(!found && !i.hasLooped) {
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

    public LongList getContigiousNumbersThatAddTo(Long target) {
        boolean found = false;
        MultiDimIndexDistinct i = new MultiDimIndexDistinct(2, this.size() + 1);
        LongList numbers = new LongList();

        while(!found && !i.hasLooped) {
            if(this.sumBetween(i.getIndex(0), i.getIndex(1)) == target) {
                for(int j = i.getIndex(0); j < i.getIndex(1); j++) {
                    numbers.add(this.get(j));
                    found = true;
                }
            }
            i.iterateForward();
        }

        return numbers;
    }

    public long sumBetween(int i, int j) {
        long sum = 0L;
        if (i < 0) i = 0;
        if (j > this.size()) j = this.size();
        for (int k = i; k < j; k++) {
            sum += this.get(k);
        }
        return sum;
    }

    public long product() {
        long product = 1;
        for(Long i : this) {
            product *= i;
        }
        return product;
    }

    public long largest() {
        Long largest = this.get(0);
        for (Long n : this) {
            if (n > largest) largest = n;
        }
        return largest;
    }

    public long smallest() {
        Long smallest = this.get(0);
        for (Long n : this) {
            if (n < smallest) smallest = n;
        }
        return smallest;
    }
}
