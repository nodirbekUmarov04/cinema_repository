package com.umarov.userservice.controller;

import com.umarov.userservice.dto.UserAddRequest;
import com.umarov.userservice.dto.UserResponse;
import com.umarov.userservice.dto.UserUpdateRequest;
import com.umarov.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserAddRequest user) {
        userService.createUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/upgradeToModerator/{id}")
    public ResponseEntity<String> upgradeToModerator(@PathVariable Long id) {
        userService.updateUserToModerator(id);
        return new ResponseEntity<>("User upgraded to moderator", HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Map<String,String>> updateUser(@PathVariable Long id,
                                                         @RequestBody UserUpdateRequest request) {
        return new ResponseEntity<>(userService.updateUser(id,request),HttpStatus.OK);
    }
}