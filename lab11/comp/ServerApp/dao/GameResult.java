package org.example.dao;

import jakarta.persistence.*;
import lombok.Data;
import org.example.PlayerStats;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "results")
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PlayerStats player;

    private int score;
    private long totalResponseTimeMs;
    private LocalDateTime timestamp;

}