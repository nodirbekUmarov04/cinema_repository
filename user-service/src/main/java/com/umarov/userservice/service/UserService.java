package com.umarov.userservice.service;

import com.umarov.exceptions.SameEmailException;
import com.umarov.exceptions.UserNotFoundException;
import com.umarov.exceptions.UsernameAlreadyTaken;
import com.umarov.userservice.dto.UserAddRequest;
import com.umarov.userservice.dto.UserResponse;
import com.umarov.userservice.dto.UserUpdateRequest;
import com.umarov.userservice.mapper.UserMapper;
import com.umarov.userservice.entity.Role;
import com.umarov.userservice.entity.User;
import com.umarov.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public void createUser(UserAddRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UsernameAlreadyTaken("Email already exists");
        }
        Role role = userRepository.count() == 0 ? Role.ADMIN : Role.USER;
        User user = new User(
                request.getEmail(),
                request.getUsername(),
                encoder.encode(request.getPassword()),
                role
        );
        userRepository.save(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.userToUserResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(
                userMapper::userToUserResponse
        ).toList();
    }

    public void updateUserToModerator(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setRole(Role.MODERATOR);
        userRepository.save(user);
    }

    @Transactional
    public Map<String,String> updateUser(Long id, UserUpdateRequest request) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        User user = userRepository.getUserById(id);
        if (request.getEmail().equals(user.getEmail())) {
            throw new SameEmailException("Please choose another email");
        }
        Map<String, String> response = new HashMap<>();
        if (request.getEmail() != null) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new UsernameAlreadyTaken("Email already exists");
            }
            user.setEmail(request.getEmail());
            response.put("newEmail", user.getEmail());
        }
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
            response.put("newUsername", user.getUsername());
        }
        if (request.getPassword() != null) {
            user.setPassword(encoder.encode(request.getPassword()));
            response.put("newPassword", "password changed");
        }
        return response;
    }
}