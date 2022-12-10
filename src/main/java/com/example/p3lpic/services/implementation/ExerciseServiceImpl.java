package com.example.p3lpic.services.implementation;

import com.example.p3lpic.DTO.ExerciseDTO;
import com.example.p3lpic.models.Exercises;
import com.example.p3lpic.models.Users;
import com.example.p3lpic.repositories.ExercisesRepository;
import com.example.p3lpic.repositories.UserRepository;
import com.example.p3lpic.services.ExerciseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * {@inheritDoc}
 */

@Service
public class ExerciseServiceImpl implements ExerciseServiceInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExercisesRepository exercisesRepository;
    final String PATH = "D:\\";
    final ArrayList<String> results = new ArrayList<>() {
        {
            add("python-exercise1-result");
            add("python-exercise2-result");
            add("python-exercise3-result");
            add("c-exercise1-result");
            add("c-exercise2-result");
            add("c-exercise3-result");
        }
    };

    @Override
    public ResponseEntity<?> uploadFiles(MultipartFile file){
        String userDir = createUserDirectory();
        String fileName = file.getOriginalFilename();

        assert fileName != null;
        try {
            file.transferTo( new File(PATH + userDir + fileName));
            if(Objects.equals(fileName.split("\\.")[1], "py")){
                if(Objects.equals(fileName, "python-exercise1") |
                        Objects.equals(fileName, "python-exercise1") |
                        Objects.equals(fileName, "python-exercise1")){
                    System.out.println("C'est maintenant qu'on run le script");
                    //TODO: script
                    //Runtime.getRuntime().exec("commande pour run le python");
                } else {
                    deleteUnauthorizedFile(fileName);
                }
            } else if(Objects.equals(fileName.split("\\.")[1], "c")) {
                if(Objects.equals(fileName, "c-exercise1") |
                        Objects.equals(fileName, "c-exercise1") |
                        Objects.equals(fileName, "c-exercise1")){
                    System.out.println("C'est maintenant qu'on run le script");
                    //TODO: script
                    //Runtime.getRuntime().exec("commande pour run le c");
                } else {
                    deleteUnauthorizedFile(fileName);
                }
            } else {
                deleteUnauthorizedFile(fileName);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @Override
    public void exerciseCheck(){
        File exerciseDirectory = new File(PATH + createUserDirectory());
        List<String> content = List.of(Objects.requireNonNull(exerciseDirectory.list()));

        results.stream().forEach((file) -> {
            if(content.contains(file+".txt")){
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(PATH+file+".txt"));
                    updateUserGrades(file, reader.readLine().equals("success"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void updateUserGrades(String file, boolean result){
        Users loggedInUser = getLoggedInUser();
        Exercises exercise = new Exercises(file+"_"+loggedInUser.getId(), result?1:0, new Date());

        if(!exercisesRepository.existsById(exercise.getName())){
            exercisesRepository.save(exercise);
            loggedInUser.getGrades().add(exercise);
        }
        userRepository.save(loggedInUser);
    }

    void deleteUnauthorizedFile(String fileName){
        try {
            Files.deleteIfExists(
                    Paths.get(PATH+fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    String createUserDirectory(){
        String userDir = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0]+"\\";
        Path path = Paths.get(PATH+userDir);
        try {
            Files.createDirectory(path);
            return userDir;
        } catch (IOException e) {
            System.out.println("Directory already exists");
            return userDir;
        }
    }

    @Override
    public List<ExerciseDTO> getExercises(){
        List<ExerciseDTO> list = new ArrayList<>();
        getLoggedInUser().getGrades().stream().forEach(e -> list.add(new ExerciseDTO(e.getName(), e.getScore(), e.getDate())));
        System.out.println(list);
        return list;
    }

    @Override
    public Users getLoggedInUser(){
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
