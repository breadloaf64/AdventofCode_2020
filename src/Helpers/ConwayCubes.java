package Helpers;

import java.util.ArrayList;

public class ConwayCubes {

    ArrayList<Triple<Integer, Integer, Integer>> activeCells;

    public ConwayCubes(ArrayList<String> input, int size) {
        activeCells = new ArrayList<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#')
                activeCells.add(new Triple<>(x, y, 0));
            }
        }
    }

    public ConwayCubes(ArrayList<Triple<Integer, Integer, Integer>> activeCells) {
        this.activeCells = activeCells;
    }

    public ConwayCubes iterate() {
        ArrayList<Triple<Integer, Integer, Integer>> nextActiveCells = new ArrayList<>();
        ArrayList<Triple<Integer, Integer, Integer>> allNeighbours = new ArrayList<>();
        for(Triple cell : activeCells) {
            allNeighbours.addAll(neighbours(cell));
        }
        for(Triple cell : unique(allNeighbours)) {
            int neighbourCount = countAppearances(allNeighbours, cell);
            if (compareCells(cell, new Triple(2, 2, 0))) {
                System.out.println();;
            }
            if (isActive(cell)) { //if cell active already
                if(neighbourCount == 2 || neighbourCount == 3) {
                    nextActiveCells.add(cell);
                }
            }
            else {
                if(neighbourCount == 3) {
                    nextActiveCells.add(cell);
                }
            }
        }
        return new ConwayCubes(nextActiveCells);
    }

    private int countAppearances(ArrayList<Triple<Integer, Integer, Integer>> cells, Triple<Integer, Integer, Integer>  cell) {
        int count = 0;
        for (Triple c : cells) {
            if (compareCells(c, cell)) count++;
        }
        return count;
    }

    private boolean compareCells(Triple<Integer, Integer, Integer> c1, Triple<Integer, Integer, Integer> c2) {
        if (c1.x.equals(c2.x) && c1.y.equals(c2.y) && c1.z.equals(c2.z)) return true;
        else return false;
    }


    private ArrayList<Triple<Integer, Integer, Integer>> unique(ArrayList<Triple<Integer, Integer, Integer>> a) {
        ArrayList<Triple<Integer, Integer, Integer>> unique = new ArrayList<>();
        for(Triple x : a) {
            if(!contains(unique, x)) unique.add(x);
        }
        return unique;
    }

    boolean contains (ArrayList<Triple<Integer, Integer, Integer>> cells, Triple<Integer, Integer, Integer> cell) {
        for (Triple c : cells) {
            if (compareCells(c, cell)) {
                return true;
            }
        }
        return false;
    }

    ArrayList<Triple<Integer, Integer, Integer>> neighbours(Triple<Integer, Integer, Integer> cell) {
        ArrayList<Triple<Integer, Integer, Integer>> neighbours = new ArrayList<>();
        int x = cell.x, y = cell.y, z = cell.z;

        neighbours.add(new Triple<>(x - 1, y - 1, z - 1));
        neighbours.add(new Triple<>(x + 0, y - 1, z - 1));
        neighbours.add(new Triple<>(x + 1, y - 1, z - 1));
        neighbours.add(new Triple<>(x - 1, y + 0, z - 1));
        neighbours.add(new Triple<>(x + 0, y + 0, z - 1));
        neighbours.add(new Triple<>(x + 1, y + 0, z - 1));
        neighbours.add(new Triple<>(x - 1, y + 1, z - 1));
        neighbours.add(new Triple<>(x + 0, y + 1, z - 1));
        neighbours.add(new Triple<>(x + 1, y + 1, z - 1));

        neighbours.add(new Triple<>(x - 1, y - 1, z + 0));
        neighbours.add(new Triple<>(x + 0, y - 1, z + 0));
        neighbours.add(new Triple<>(x + 1, y - 1, z + 0));
        neighbours.add(new Triple<>(x - 1, y + 0, z + 0));
        //neighbours.add(new Triple<>(x + 0, y + 0, z + 0));
        neighbours.add(new Triple<>(x + 1, y + 0, z + 0));
        neighbours.add(new Triple<>(x - 1, y + 1, z + 0));
        neighbours.add(new Triple<>(x + 0, y + 1, z + 0));
        neighbours.add(new Triple<>(x + 1, y + 1, z + 0));

        neighbours.add(new Triple<>(x - 1, y - 1, z + 1));
        neighbours.add(new Triple<>(x + 0, y - 1, z + 1));
        neighbours.add(new Triple<>(x + 1, y - 1, z + 1));
        neighbours.add(new Triple<>(x - 1, y + 0, z + 1));
        neighbours.add(new Triple<>(x + 0, y + 0, z + 1));
        neighbours.add(new Triple<>(x + 1, y + 0, z + 1));
        neighbours.add(new Triple<>(x - 1, y + 1, z + 1));
        neighbours.add(new Triple<>(x + 0, y + 1, z + 1));
        neighbours.add(new Triple<>(x + 1, y + 1, z + 1));

        return neighbours;
    }


    public int count() {
        return activeCells.size();
    }

    public void draw() {
        if (!(activeCells.size() == 0)) {
            int minx = xSpan().x, maxx = xSpan().y, miny = ySpan().x, maxy = ySpan().y, minz = zSpan().x, maxz = zSpan().y;
            System.out.println("Top left is: " + minx + ", " + miny);
            String row;
            for(int z = minz; z <= maxz; z++) {
                System.out.println("z = " + z);
                for (int y = miny; y <= maxy; y++) {
                    row = "";
                    for (int x = minx; x <= maxx; x++) {
                        if (isActive(x, y, z)) row += '#';
                        else row += '.';
                    }
                    System.out.println(row);
                }
            }
        }
    }

    private boolean isActive(Triple<Integer, Integer, Integer> cell) {
        for (Triple c : activeCells) {
            if(c.x.equals(cell.x) && c.y.equals(cell.y) && c.z.equals(cell.z)) return true;
        }
        return false;
    }

    private boolean isActive(int x, int y, int z) {
        for (Triple c : activeCells) {
            if(c.x.equals(x) && c.y.equals(y) && c.z.equals(z)) return true;
        }
        return false;
    }

    private Tuple<Integer, Integer> xSpan() {
        int minx = activeCells.get(0).x;
        int maxx = minx;
        for (Triple<Integer, Integer, Integer> cell : activeCells) {
            if (cell.x > maxx) maxx = cell.x;
            if (cell.x < minx) minx = cell.x;
        }
        return new Tuple<>(minx, maxx);
    }

    private Tuple<Integer, Integer> ySpan() {
        int miny = activeCells.get(0).y;
        int maxy = miny;
        for (Triple<Integer, Integer, Integer> cell : activeCells) {
            if (cell.y > maxy) maxy = cell.y;
            if (cell.y < miny) miny = cell.y;
        }
        return new Tuple<>(miny, maxy);
    }

    private Tuple<Integer, Integer> zSpan() {
        int minz = activeCells.get(0).z;
        int maxz = minz;
        for (Triple<Integer, Integer, Integer> cell : activeCells) {
            if (cell.z > maxz) maxz = cell.z;
            if (cell.z < minz) minz = cell.z;
        }
        return new Tuple<>(minz, maxz);
    }
}
