package org.example.compulsory;

import lombok.Getter;
import lombok.Setter;
import org.example.advance.Concepts;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.EnumSet;
import java.util.List;

@Setter
@Getter
public class Resource {

    private String id;
    private String title;
    private URI location;
    private int year;
    private String author;
    private EnumSet<Concepts> conceptsList;

    public Resource(String id, String title, URI location, int year, String author, EnumSet<Concepts> concepts) {
        this.id = id;
        this.title = title;
        this.location = location;
        if (year < 0) {
            throw new InvalidYearException(year);
        } else {
            this.year = year;
        }
        this.author = author;
        this.conceptsList = concepts;
    }


    public void setYear(int year) {
        if (year < 0) {
            throw new InvalidYearException(year);
        } else {
            this.year = year;
        }
    }


    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location=" + location +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", conceptsList=" + conceptsList +
                "} \n";
    }

    public void openResource() {
        Desktop desktop = Desktop.getDesktop();
        try {
            if (this.getLocation().getScheme().startsWith("http")) {
                desktop.browse(this.getLocation());
            } else {
                desktop.open(new File(this.getLocation().getPath()));
            }
        } catch (IOException e) {
            System.err.println("Nu s-a putut deschide locatia: " + e.getMessage());
        }

    }
}
