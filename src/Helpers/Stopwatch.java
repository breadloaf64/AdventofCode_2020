package Helpers;

public class Stopwatch {
    long startTime;
    long endTime;
    boolean running;

    public Stopwatch() {
        running = false;
    }

    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public long check() {
        if (running) return System.currentTimeMillis() - startTime;
        else return endTime - startTime;

    }

    public long stop() {
        endTime = System.currentTimeMillis() - startTime;
        running = false;
        return endTime;
    }

    public String printTime(int mode) {
        String out = "";
        Double time;
        switch (mode){
            case 0:
                out = String.valueOf(startTime - endTime) + " ms";
                break;

            case 1:
                time = Double.valueOf((startTime - endTime)) / 1000;
                out = String.valueOf(time) + " s";
                break;
        }
        System.out.println("Time: " + out);
    return out;
    }
}
