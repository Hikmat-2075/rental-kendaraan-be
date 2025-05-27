package com.kelompok3.rental_kendaraan_be.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok3.rental_kendaraan_be.model.User;
import com.kelompok3.rental_kendaraan_be.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Cek jika username atau email sudah digunakan
        if (userService.getUserByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username sudah digunakan");
        }
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email sudah digunakan");
        }
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        Optional<User> userOpt = userService.getUserByUsername(loginRequest.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(401).body("Username atau password salah");
    }
}
