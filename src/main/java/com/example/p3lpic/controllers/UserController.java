package com.example.p3lpic.controllers;

import com.example.p3lpic.models.Users;
import com.example.p3lpic.services.UserServiceIntefrace;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user related stuff
 * @author Axelle Lacquemant
 */

@RestController
public class UserController {
    private final UserServiceIntefrace service;

    private final PasswordEncoder encoder;

    public UserController(UserServiceIntefrace service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        Users user1 = new Users();
        user1.setEmail(user.getEmail());
        user1.setPassword(encoder.encode(user.getPassword()));
        user1.setSrole(user.getSrole());
        return service.register(user1);
    }

    @GetMapping("/userInfo")
    public Users getUserInfo(@RequestParam("email") String email) {
        return service.getDetails(email);
    }

    @GetMapping("/getUserRoles")
    public String getUserRole(@RequestParam("email") String email) {
        return service.getUserRole(email);
    }
}
