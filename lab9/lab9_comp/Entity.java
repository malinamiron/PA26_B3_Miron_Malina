package org.example.lab9_comp;

import java.util.*;

public abstract class Entity implements Runnable {
    protected String name;
    protected int r;
    protected int c;
    protected Maze maze;
    protected static boolean gameRunning = true;

    public Entity(String name, int r, int c, Maze maze) {
        this.name = name;
        this.r = r;
        this.c = c;
        this.maze = maze;
    }

    protected boolean moveRandomly() {
        List<Integer> directions = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        Collections.shuffle(directions);

        for (int dir : directions) {
            if (!maze.grid[r][c].walls[dir]) {
                int nextR = r, nextC = c;
                if (dir == 0) nextR--;      // Sus
                else if (dir == 1) nextC++; // Dreapta
                else if (dir == 2) nextR++; // Jos
                else if (dir == 3) nextC--; // Stânga

                if (maze.grid[nextR][nextC].tryEnter()) {
                    maze.grid[r][c].leave();
                    this.r = nextR;
                    this.c = nextC;
                    return true;
                }
            }
        }
        return false;
    }
}