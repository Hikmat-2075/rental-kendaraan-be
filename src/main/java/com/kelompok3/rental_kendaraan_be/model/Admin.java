package com.kelompok3.rental_kendaraan_be.model;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    // Default constructor (wajib untuk JPA)
    public Admin() {
        super();
    }

    // Constructor lengkap
    public Admin(String username, String password, String email, String namaLengkap, LocalDateTime createdAt) {
        super(username, password, email, namaLengkap, "ADMIN", createdAt);
    }

    // Tidak ada method tambahan di model, hanya getter/setter (sudah diwarisi dari User)
}
