package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//Paksi
@Entity
@DiscriminatorValue("admin")
public class Admin extends User{
    
}
