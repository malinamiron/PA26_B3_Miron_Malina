package org.example;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "players")
@NoArgsConstructor
public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score = 0;
    private long totalResponseTime = 0;
    @Column(unique = true)
    private String name;

    public PlayerStats(String name) {
        this.name = name;
    }

    public synchronized void addResult(boolean correct, long timeTaken) {
        if (correct) score++;
        this.totalResponseTime += timeTaken;
    }
}