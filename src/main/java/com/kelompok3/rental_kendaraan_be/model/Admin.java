package com.kelompok3.rental_kendaraan_be.model;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//Paksi
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    // Default constructor (wajib untuk JPA)
    public Admin() {
        super();
    }

    // Constructor lengkap sesuai User
    public Admin(String username, String password, String email, String namaLengkap, LocalDateTime createdAt) {
        super(username, password, email, namaLengkap, "ADMIN", createdAt);
    }

    // Method-method logis (kerangka kosong dulu)
    public void konfirmasiTransaksi(Long transaksiId) {
        // TODO: panggil TransaksiService atau inject dan update status
    }

    public void hapusKendaraan(Long kendaraanId) {
        // TODO: panggil KendaraanService untuk menghapus
    }

    public void tambahKendaraan(Object kendaraan) {
        // TODO: panggil KendaraanService atau repository
    }

    public void lihatRiwayat() {
        // TODO: ambil data dari RiwayatTransaksiService
    }
}
