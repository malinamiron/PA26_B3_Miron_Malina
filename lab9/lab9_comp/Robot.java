package org.example.lab9_comp;

class Robot extends Entity {
    private SharedMemory memory;

    public Robot(String name, int r, int c, Maze maze, SharedMemory memory) {
        super(name, r, c, maze);
        this.memory = memory;
    }

    @Override
    public void run() {
        while (gameRunning) {
            moveRandomly();
            System.out.println(name + " 🤖 caută la [" + r + "," + c + "]");

            if (r == maze.bunny.r && c == maze.bunny.c) {
                System.out.println("🛑 " + name + " a prins iepurele la [" + r + "," + c + "]!");
                gameRunning = false;
            }

            try { Thread.sleep(600); } catch (InterruptedException e) {}
        }
    }
}