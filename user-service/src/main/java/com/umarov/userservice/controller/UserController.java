package com.umarov.userservice.controller;


import com.umarov.userservice.model.UserData;
import com.umarov.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserData createUser(@RequestBody UserData user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public UserData getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserData> getAllUsers() {
        return userService.getAllUsers();
    }
}