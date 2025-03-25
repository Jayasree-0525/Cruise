package com.example.cms.controller;

import com.example.cms.model.repository.UserRepository;
import com.example.cms.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Use 'repository' to call 'findByUsername'
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("status", "error",
                    "message", "Invalid username or password"));
        }

        String sessionToken = UUID.randomUUID().toString();
        return ResponseEntity.ok(Map.of("username", username,
                "status", "success",
                "token", sessionToken,
                "isAdmin", user.getIsAdmin()));
    }
}
