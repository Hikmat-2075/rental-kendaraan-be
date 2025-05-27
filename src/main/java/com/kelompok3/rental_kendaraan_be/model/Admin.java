package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

//Paksi
@Entity
@DiscriminatorValue("admin")
public class Admin extends User{
    // Default constructor
     public Admin(String name, String email, String password) {
        super(); // akan menyesuaikan constructor User nanti
        
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
