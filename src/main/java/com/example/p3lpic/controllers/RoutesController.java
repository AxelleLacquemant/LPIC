package com.example.p3lpic.controllers;

import com.example.p3lpic.services.ExerciseServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class RoutesController {
    private final ExerciseServiceInterface exerciseInterface;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/exercises")
    public String exercises(){
        return "exercises";
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file ){
        return exerciseInterface.uploadFiles(file);
    }

    @GetMapping("/exercises/corrections")
    public String corrections(Model model){
        exerciseInterface.exerciseCheck();

        model.addAttribute("loggedInUser", exerciseInterface.getLoggedInUser());
        model.addAttribute("grades", exerciseInterface.getExercises());
        /*
        exerciseInterface.getLoggedInUser().getGrades().stream().forEach(e -> {
            System.out.println(e.getName()+": "+e.getScore());
        });
         */
        return "corrections";
    }
}