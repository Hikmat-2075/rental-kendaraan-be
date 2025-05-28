package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

    public Admin() {
        super();
    }

    // Tambahkan method atau field khusus admin jika diperlukan

}
