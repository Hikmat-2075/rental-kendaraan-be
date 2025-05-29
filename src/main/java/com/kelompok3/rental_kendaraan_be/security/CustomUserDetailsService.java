package com.kelompok3.rental_kendaraan_be.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kelompok3.rental_kendaraan_be.model.User;
import com.kelompok3.rental_kendaraan_be.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Spring Security will call this method during login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        // Return Spring Security's User object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()) // atau getUsername() jika itu identitasnya
                .password(user.getPassword())
                .roles(user.getRole()) // pastikan ini berupa String seperti "USER", "ADMIN"
                .build();
    }
}

