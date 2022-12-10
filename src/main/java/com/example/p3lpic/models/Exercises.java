package com.example.p3lpic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exercises {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "score")
    private int score;

    @Column(name = "date")
    private Date date;
}
