package Helpers;

import java.util.ArrayList;

public final class FerryAutomaton {
    char[][] arrangement;
    int w;
    int h;
    boolean sameAsPrevious;

    public FerryAutomaton(ArrayList<String> input) {
        w = input.get(0).length();
        h = input.size();
        arrangement = new char[h][w];

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                arrangement[r][c] = input.get(r).charAt(c);
            }
        }
        sameAsPrevious = false;
    }

    public FerryAutomaton(char[][] arrangement, boolean sameAsPrevious) {
        this.arrangement = arrangement;
        h = arrangement.length;
        w = arrangement[0].length;
        this.sameAsPrevious = sameAsPrevious;
    }

    public FerryAutomaton next() {
        char[][] nextArrangement = cloneArray(arrangement);
        boolean same = true;
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                char current = arrangement[r][c];
                int neighbours = countNeighbours(r, c);
                if (arrangement[r][c] == 'L' && countNeighbours(r, c) == 0) {
                    nextArrangement[r][c] = '#';
                    same = false;
                }
                else if (arrangement[r][c] == '#' && countNeighbours(r, c) >= 4) {
                    nextArrangement[r][c] = 'L';
                    same = false;
                }
            }
        }
        return new FerryAutomaton(nextArrangement, same);
    }

    public FerryAutomaton nextVision() {
        char[][] nextArrangement = cloneArray(arrangement);
        boolean same = true;
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                char current = arrangement[r][c];
                int neighbours = countVisible(r, c);
                if (arrangement[r][c] == 'L' && countVisible(r, c) == 0) {
                    nextArrangement[r][c] = '#';
                    same = false;
                }
                else if (arrangement[r][c] == '#' && countVisible(r, c) >= 5) {
                    nextArrangement[r][c] = 'L';
                    same = false;
                }
            }
        }
        return new FerryAutomaton(nextArrangement, same);
    }

    private int countNeighbours(int r, int c) {
        int count = 0;
        if (isOccupied(r - 1, c - 1)) count++;
        if (isOccupied(r - 1, c)) count++;
        if (isOccupied(r - 1, c + 1)) count++;
        if (isOccupied(r, c - 1)) count++;
        if (isOccupied(r, c + 1)) count++;
        if (isOccupied(r + 1, c - 1)) count++;
        if (isOccupied(r + 1, c)) count++;
        if (isOccupied(r + 1, c + 1)) count++;
        return count;
    }

    private int countVisible(int r, int c) {
        int count = 0;
        if (scanDirection(r, c, 1, -1)) count++;
        if (scanDirection(r, c, 0, -1)) count++;
        if (scanDirection(r, c, -1, -1)) count++;
        if (scanDirection(r, c, 1, 0)) count++;
        if (scanDirection(r, c, -1, 0)) count++;
        if (scanDirection(r, c, 1, 1)) count++;
        if (scanDirection(r, c, 0, 1)) count++;
        if (scanDirection(r, c, -1, 1)) count++;
        return count;
    }

    private boolean scanDirection(int r, int c, int dr, int dc) {
        int cr = r, cc = c; //current row and column
        cr += dr;
        cc += dc;
        while (withinBounds(cr, cc)) {
            if (arrangement[cr][cc] == '#') {
                return true;
            }
            if (arrangement[cr][cc] == 'L') {
                return false;
            }
            cr += dr;
            cc += dc;
        }
        return false;
    }

    private boolean withinBounds(int r, int c) {
        return 0 <= r && r < h && 0 <= c && c < w;
    }

    private boolean isOccupied(int r, int c) {
        if(withinBounds(r, c)) {
            return arrangement[r][c] == '#';
        }
        else {
            return false;
        }

    }

    public int countOccupied() {
        int count = 0;
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (arrangement[r][c] == '#') count++;
            }
        }
        return count;
    }

    public void print() {
        String row;

        for (int r = 0; r < h; r++) {
            row = "";
            for (int c = 0; c < w; c++) {
                row += arrangement[r][c];
            }
            System.out.println(row);
        }
    }

    public boolean hasChanged() {return !sameAsPrevious;}

    public static char[][] cloneArray(char[][] src) {
        int length = src.length;
        char[][] target = new char[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }
}
