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

    public long product() {
        long product = 1;
        for(Long i : this) {
            product *= i;
        }
        return product;
    }
}
