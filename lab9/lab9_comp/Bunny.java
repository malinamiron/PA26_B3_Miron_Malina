package org.example.lab9_comp;

public class Bunny extends Entity {
    public Bunny(int r, int c, Maze maze) { super("🐰 Bunny", r, c, maze); }

    @Override
    public void run() {
        while (gameRunning) {
            moveRandomly();
            System.out.println(name + " s-a mutat la [" + r + "," + c + "]");

            if (r == maze.rows - 1 && c == maze.cols - 1) {
                System.out.println("🎉 Iepurele a găsit ieșirea! JOC AJUNS LA FINAL.");
                gameRunning = false;
            }
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }
}

