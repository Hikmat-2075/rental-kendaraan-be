package com.kelompok3.rental_kendaraan_be.controller;

import com.kelompok3.rental_kendaraan_be.dto.LoginRequest;
import com.kelompok3.rental_kendaraan_be.dto.JwtResponse;
import com.kelompok3.rental_kendaraan_be.dto.RegisterRequest;
import com.kelompok3.rental_kendaraan_be.model.User;
import com.kelompok3.rental_kendaraan_be.service.UserService;
import com.kelompok3.rental_kendaraan_be.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Endpoint untuk Register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Cek jika email sudah terdaftar
        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        // Membuat user baru
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());  // Jangan lupa enkripsi password sebelum menyimpannya!
        user.setEmail(registerRequest.getEmail());
        user.setNamaLengkap(registerRequest.getNamaLengkap());
        user.setNoTelepon(registerRequest.getNoTelepon());

        User createdUser = userService.createUser(user);

        // Menghasilkan token setelah berhasil registrasi
        String token = jwtTokenProvider.createToken(createdUser.getUsername());

        // Response dengan token
        return ResponseEntity.ok(new JwtResponse(token));
    }

    // Endpoint untuk Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Mencari user berdasarkan username
        User user = userService.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + loginRequest.getUsername()));

        // Memeriksa password (gunakan metode yang sesuai, misalnya menggunakan bcrypt untuk enkripsi password)
        if (!userService.checkPassword(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Generate JWT token menggunakan JwtTokenProvider
        String token = jwtTokenProvider.createToken(user.getUsername());

        // Mengirimkan token sebagai bagian dari response
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
