package org.example.lab9_comp;

import java.awt.Point;

public class SharedMemory {
    private Point bunnyLocation = null;

    public synchronized void updateBunnyLocation(int r, int c) {
        this.bunnyLocation = new Point(r, c);
    }

    public synchronized Point getBunnyLocation() {
        return bunnyLocation;
    }
}