package com.example.p3lpic.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service interface for exercises
 * @author Axelle Lacquemant
 */

public interface ExerciseServiceInterface {
    ResponseEntity<?> uploadFiles(MultipartFile file);

    void exerciseCheck();
}
