package org.example.lab8;

public class Cell {
    int rand, coloana;

    boolean[] walls = {true, true, true, true};

    public Cell(int rand, int coloana) {
        this.rand = rand;
        this.coloana = coloana;
    }
}