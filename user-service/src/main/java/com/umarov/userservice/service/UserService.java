package com.umarov.userservice.service;


import com.umarov.userservice.model.UserData;
import com.umarov.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserData createUser(UserData user) {
        return userRepository.save(user);
    }

    public UserData getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserData> getAllUsers() {
        return userRepository.findAll();
    }
}