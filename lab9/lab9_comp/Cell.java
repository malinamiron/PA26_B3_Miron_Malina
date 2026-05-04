package org.example.lab9_comp;

public class Cell {
    final int r, c;
    boolean[] walls = {true, true, true, true}; // Sus, Dreapta, Jos, Stânga
    private boolean occupied = false;

    public Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public synchronized boolean tryEnter() {
        if (occupied) return false;
        occupied = true;
        return true;
    }

    public synchronized void leave() {
        occupied = false;
    }
}