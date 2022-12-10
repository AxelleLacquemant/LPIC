package com.example.p3lpic.repositories;

import com.example.p3lpic.models.Exercises;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercisesRepository extends JpaRepository<Exercises, String> {
    Exercises findByName(String name);
}
