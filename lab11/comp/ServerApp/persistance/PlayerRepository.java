package org.example.persistance;

import jakarta.persistence.EntityManager;
import org.example.PlayerStats;

import java.util.Optional;

public class PlayerRepository {
    private EntityManager em;

    public PlayerRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<PlayerStats> findByName(String name) {
        return em.createQuery("SELECT p FROM PlayerStats p WHERE p.name = :name", PlayerStats.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

    public PlayerStats create(String name) {
        PlayerStats player = new PlayerStats(name);
        em.persist(player);
        return player;
    }
}