package com.spring.practice.bookstorebackend.controller;

import com.spring.practice.bookstorebackend.entity.User;
import com.spring.practice.bookstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }


}
