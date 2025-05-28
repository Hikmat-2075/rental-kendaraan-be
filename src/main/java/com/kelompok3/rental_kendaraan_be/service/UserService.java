package com.kelompok3.rental_kendaraan_be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kelompok3.rental_kendaraan_be.model.User;
import com.kelompok3.rental_kendaraan_be.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User by ID with Exception Handling
    public User getUserById(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // Get User by Username with Exception Handling
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new RuntimeException("User not found with username " + username));
    }

    // Get User by Email with Exception Handling
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new RuntimeException("User not found with email " + email));
    }

    // Save User and Check Duplicate (with password hashing)
    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        // Encode password sebelum menyimpan
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Delete User with Exception Handling
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
