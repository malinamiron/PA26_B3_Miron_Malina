package org.example.lab6_a;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieList {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private List<Movie> movies = new ArrayList<>();

}