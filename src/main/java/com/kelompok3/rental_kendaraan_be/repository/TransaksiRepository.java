package com.kelompok3.rental_kendaraan_be.repository;

import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
///mengelola data transaksi kendaraan
@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    List<Transaksi> findByUserId(Long userId);
}
