package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.Questions;
import org.example.persistance.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

public class QuestionLoader {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizPU");
     private static final EntityManager em = emf.createEntityManager();
     private static final QuestionRepository questionRepo = new QuestionRepository(em);
    public static List<Questions> loadQuestions() {
        List<Questions> questions = new ArrayList<>();

        questions = questionRepo.findAll();

        if (questions.isEmpty()) {
            System.out.println("No questions found in database. Seeding...");
            seedDatabase();
            questions = questionRepo.findAll();
        }
        System.out.println("Loaded " + questions.size() + " questions from the database.");
        return questions;
    }

    private static void seedDatabase() {
        em.getTransaction().begin();
        try {
            Questions q1 = new Questions();
            q1.setText("Care este capitala Frantei?");
            q1.setOptions("Paris,Londra,Berlin,Madrid");
            q1.setCorrectIndex(1);
            q1.setTimeLimitSeconds(10);
            em.persist(q1);

            Questions q2 = new Questions();
            q2.setText("Cat face 5+5?");
            q2.setOptions("2,10,15,20");
            q2.setCorrectIndex(2);
            q2.setTimeLimitSeconds(15);
            em.persist(q2);

            Questions q3 = new Questions();
            q3.setText("Cine a scris Luceafarul?");
            q3.setOptions("Bacovia,Arghezi,Eminescu,Cartarescu");
            q3.setCorrectIndex(3);
            q3.setTimeLimitSeconds(5);
            em.persist(q3);

            em.getTransaction().commit();
            System.out.println("Database seeded with sample questions.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }


}