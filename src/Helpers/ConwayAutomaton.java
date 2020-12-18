package Helpers;

import java.util.ArrayList;

public class ConwayAutomaton {

    ArrayList<Cell> activeCells;
    int dimension;

    public ConwayAutomaton(ArrayList<String> input, int dimension, boolean redundant) {
        this.dimension = dimension;
        activeCells = new ArrayList<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#')
                    activeCells.add(new Cell(dimension, x, y));
            }
        }
    }

    public ConwayAutomaton(ArrayList<Cell> activeCells, int dimension) {
        this.dimension = dimension;
        this.activeCells = activeCells;
    }

    public ConwayAutomaton iterate() {
        ArrayList<Cell> nextActiveCells = new ArrayList<>();
        ArrayList<Cell> allNeighbours = new ArrayList<>();
        for(Cell cell : activeCells) {
            allNeighbours.addAll(cell.neighbours());
        }
        for(Cell cell : unique(allNeighbours)) {
            int neighbourCount = countAppearances(allNeighbours, cell);
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
        return new ConwayAutomaton(nextActiveCells, dimension);
    }

    private boolean isActive(Cell cell) {
        for (Cell c : activeCells) {
            if(c.is(cell)) return true;
        }
        return false;
    }

    private int countAppearances(ArrayList<Cell> cells, Cell cell) {
        int count = 0;
        for (Cell c : cells) {
            if (c.is(cell)) count++;
        }
        return count;
    }

    public int count() {
        return activeCells.size();
    }

    private ArrayList<Cell> unique(ArrayList<Cell> a) {
        ArrayList<Cell> unique = new ArrayList<>();
        for(Cell x : a) {
            if(!contains(unique, x)) unique.add(x);
        }
        return unique;
    }

    boolean contains (ArrayList<Cell> cells, Cell cell) {
        for (Cell c : cells) {
            if (c.is(cell)) {
                return true;
            }
        }
        return false;
    }


    static class Cell {
        int dimension;
        ArrayList<Integer> coordinates;

        Cell(int dimension, int x, int y) {
            this.dimension = dimension;
            coordinates = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                if (i == 0) coordinates.add(x);
                else if (i == 1) coordinates.add(y);
                else coordinates.add(0);
            }
        }

        Cell (int dimension, ArrayList<Integer> coordinates) {
            this.dimension = dimension;
            this.coordinates = coordinates;
        }

        boolean is(Cell c) {
            for (int i = 0; i < dimension; i++) {
                if (!c.coordinates.get(i).equals(this.coordinates.get(i))) return false;
            }
            return true;
        }

        ArrayList<Cell> neighbours() {
            ArrayList<Cell> neighbours = new ArrayList<>();
            MultiDimIndex i = new MultiDimIndex(dimension, 3);
            ArrayList<Integer> neighbourCoords;
            Cell neighbour;
            while (!i.hasLooped()) {
                neighbourCoords = new ArrayList<>();
                for (int j = 0; j < dimension; j++) {
                    neighbourCoords.add(coordinates.get(j) + i.getIndex(j) - 1);
                }
                neighbour = new Cell(dimension, neighbourCoords);
                if (!neighbour.is(this)) neighbours.add(neighbour);
                i.iterateForward();
            }
            return neighbours;
        }
    }
}
