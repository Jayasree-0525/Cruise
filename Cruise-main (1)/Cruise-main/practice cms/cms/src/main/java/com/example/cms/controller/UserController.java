package com.example.cms.controller;

import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //    private
    @GetMapping
    List<User> getAllUsers(){return userRepository.findAll();}

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String rawPassword = userData.get("password");

        // check if user already exists
        if (userRepository.findByUsername(username).isPresent()) {
            System.err.println("Error: Username '" + username + "' is already taken.");
            return ResponseEntity.badRequest().body(Map.of("error", "Username already taken"));
        }
        // create a new user with isAdmin set to default, this attribute should be set by a super admin user
        User newUser = new User(username, rawPassword);
        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // search username in the database
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // verify password
        if (!password.equals(user.getPassword())){
            return ResponseEntity.status(401).body(Map.of("status", "error",
                    "message", "Invalid username or password"));
        }
        // optionally, generate session token for Appsmith
        String sessionToken = UUID.randomUUID().toString();
        return ResponseEntity.ok(Map.of( "username", username,
                "status", "success",
                "token", sessionToken,
                "isAdmin", user.getIsAdmin()));
    }

}

