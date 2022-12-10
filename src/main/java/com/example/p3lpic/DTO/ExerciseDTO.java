package com.example.p3lpic.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExerciseDTO {
    private String name;
    private String score;
    private String date;

    public ExerciseDTO(String name, int score, Date date) {
        String[] prettyName = name.split("-");
        String language = prettyName[0].length() > 1? prettyName[0].substring(0, 1).toUpperCase()+prettyName[0].substring(1) : prettyName[0];

        this.name = language+": "+prettyName[1].replace("e1", "e 1");
        this.score = (score==1?"Success":"Failure");
        this.date = date.toString().split("\\.")[0].replace(" ", ", ");
    }
}
