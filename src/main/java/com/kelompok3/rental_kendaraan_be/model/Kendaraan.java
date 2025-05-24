package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

//Bram
@Entity
@Table(name = "kendaraan")
@Inheritance(strategy = InheritanceType.JOINED)
public class Kendaraan {
    
}
