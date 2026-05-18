package org.example;

public class Questions {
    public record Question(String text, String[] options, int correctIndex, int timeLimitSeconds) {}
}
