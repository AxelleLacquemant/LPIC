package com.example.p3lpic.services.implementation;

import com.example.p3lpic.models.Users;
import com.example.p3lpic.repositories.UserRepository;
import com.example.p3lpic.services.UserServiceIntefrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 */

@Service
public class UserServiceImpl implements UserServiceIntefrace {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Users register(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getDetails(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String getUserRole(String email) {
        return userRepository.findByEmail(email).getSrole();
    }
}
