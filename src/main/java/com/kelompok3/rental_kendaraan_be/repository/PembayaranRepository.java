package com.kelompok3.rental_kendaraan_be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kelompok3.rental_kendaraan_be.model.Pembayaran;

//Arief
@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, Long> {
    // Tambahkan custom query jika dibutuhkan
}

