package org.example.lab9_comp;
import java.util.Random;

public class Maze {
    int rows, cols;
    Cell[][] grid;
    Bunny bunny;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) grid[i][j] = new Cell(i, j);

        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i > 0) { grid[i][j].walls[0] = false; grid[i-1][j].walls[2] = false; }
                if (j > 0) { grid[i][j].walls[3] = false; grid[i][j-1].walls[1] = false; }
            }
        }
    }

    public void startSimulation(int numRobots) {
        SharedMemory memory = new SharedMemory();

        bunny = new Bunny(0, 0, this);
        grid[0][0].tryEnter();
        new Thread(bunny).start();

        Random rand = new Random();
        for (int i = 0; i < numRobots; i++) {
            int rr, rc;
            do {
                rr = rand.nextInt(rows);
                rc = rand.nextInt(cols);
            } while (!grid[rr][rc].tryEnter());

            new Thread(new Robot("Robot-" + i, rr, rc, this, memory)).start();
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze(5, 5);
        maze.startSimulation(3);
    }
}