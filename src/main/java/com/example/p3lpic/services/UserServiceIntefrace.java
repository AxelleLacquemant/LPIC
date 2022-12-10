package com.example.p3lpic.services;

import com.example.p3lpic.models.Users;

/**
 * Service interface for user
 * @author Axelle Lacquemant
 */

public interface UserServiceIntefrace {
    Users register(Users user);

    Users getDetails(String email);

    String getUserRole(String email);
}
