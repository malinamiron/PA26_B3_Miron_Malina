package org.example;

import java.io.*;
import java.util.*;

public class QuestionLoader {
    public static List<Questions.Question> loadQuestions(String filePath) {
        List<Questions.Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String text = parts[0];
                    String[] options = parts[1].split(",");
                    int correctIndex = Integer.parseInt(parts[2]);
                    int timeLimit = Integer.parseInt(parts[3]);
                    questions.add(new Questions.Question(text, options, correctIndex, timeLimit));
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului de intrebari: " + e.getMessage());
        }
        return questions;
    }
}