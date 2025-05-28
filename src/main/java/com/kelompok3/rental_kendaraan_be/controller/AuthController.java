package com.kelompok3.rental_kendaraan_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok3.rental_kendaraan_be.dto.JwtResponse;
import com.kelompok3.rental_kendaraan_be.dto.LoginRequest;
import com.kelompok3.rental_kendaraan_be.dto.RegisterRequest;
import com.kelompok3.rental_kendaraan_be.model.User;
import com.kelompok3.rental_kendaraan_be.security.JwtUtil;
import com.kelompok3.rental_kendaraan_be.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")  // Sesuaikan dengan frontend Anda
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Autowire PasswordEncoder langsung di sini

    // Endpoint login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Verifikasi username
            User user = userService.getUserByUsername(request.getUsername());

            // Cek password yang di-hash
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }

            // Generate JWT Token jika login berhasil
            String token = jwtUtil.generateToken(request.getUsername());

            // Return response dengan token dalam bentuk JwtResponse
            JwtResponse jwtResponse = new JwtResponse(token);
            return ResponseEntity.ok(jwtResponse);  // Kembalikan token dalam bentuk response

        } catch (RuntimeException e) {
            // Jika terjadi error seperti user tidak ditemukan
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // Endpoint untuk register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // Membuat User dari RegisterRequest
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));  // Enkripsi password
            user.setEmail(registerRequest.getEmail());

            return ResponseEntity.ok(userService.saveUser(user));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
