package org.example.lab6_a;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
public class Movie {
    private int id;
    private String title;
    private Date releaseDate;
    private int duration;
    private float score;
    private int genreId;

}