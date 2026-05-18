package org.example;

public class PlayerStats {
    private int score = 0;
    private long totalResponseTime = 0;

    public synchronized void addResult(boolean correct, long timeTaken) {
        if (correct) score++;
        this.totalResponseTime += timeTaken;
    }

    public int getScore() { return score; }
    public long getTotalResponseTime() { return totalResponseTime; }
}