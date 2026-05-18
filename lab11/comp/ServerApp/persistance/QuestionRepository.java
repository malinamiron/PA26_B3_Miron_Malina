package org.example.persistance;

import jakarta.persistence.EntityManager;
import org.example.dao.Questions;

import java.util.List;

public class QuestionRepository {
    private EntityManager entityMan;

    public QuestionRepository(EntityManager entityMan) {
        this.entityMan = entityMan;
    }

    public List<Questions> findAll() {
        return entityMan.createQuery("SELECT q FROM Questions q", Questions.class).getResultList();
    }

    public void create(Questions question) {
        entityMan.getTransaction().begin();
        entityMan.persist(question);
        entityMan.getTransaction().commit();
    }
}