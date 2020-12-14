package Helpers;

import java.util.Objects;

public class GridWalker {
    public double x;
    public double y; // negative y is north
    public double d; // clockwise heading from east (positive x)

    public GridWalker(double x, double y, double d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public void runCommand(String command) {
        String op = command.substring(0, 1);
        int arg = Integer.parseInt(command.substring(1));

        switch (op) {
            case "N":
                y -= arg;
                break;

            case "S":
                y += arg;
                break;

            case "E":
                x += arg;
                break;

            case "W":
                x -= arg;
                break;

            case "L":
                d = (d - arg) % 360;
                break;

            case "R":
                d = (d + arg) % 360;
                break;

            case "F":
                x += Math.round(Math.cos(Math.toRadians(d))) * arg;
                y += Math.round(Math.sin(Math.toRadians(d))) * arg;
        }
    }

    public void rotateAboutOrigin(String direction, double dtheta) {
        double inter;
        if (dtheta == 180) {
            x *= -1;
            y *= -1;
        }
        else if (dtheta == 90) {
            if (Objects.equals(direction, "R")) {
                turn90R();
            }
            else {
                turn90L();
            }
        }
        else if (dtheta == 270) {
            if (Objects.equals(direction, "R")) {
                turn90L();
            }
            else {
                turn90R();
            }
        }
    }

    private void turn90L() {
        double inter = x;
        x = y;
        y = -inter;
    }

    private void turn90R() {
        double inter = x;
        x = -y;
        y = inter;
    }

    public double manhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }
}


