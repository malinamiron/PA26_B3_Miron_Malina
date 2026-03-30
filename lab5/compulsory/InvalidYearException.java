package org.example.compulsory;

public class InvalidYearException extends RuntimeException {
    private final int year;
    public InvalidYearException(int year) {
        super("Invalid year: " + year);
        this.year = year;
    }
    public int getYear() {
        return year;
    }

}
