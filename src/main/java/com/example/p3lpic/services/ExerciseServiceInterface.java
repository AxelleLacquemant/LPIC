package com.example.p3lpic.services;

import com.example.p3lpic.DTO.ExerciseDTO;
import com.example.p3lpic.models.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for exercises
 * @author Axelle Lacquemant
 */

public interface ExerciseServiceInterface {
    ResponseEntity<?> uploadFiles(MultipartFile file);

    void exerciseCheck();

    List<ExerciseDTO> getExercises();

    Users getLoggedInUser();
}
