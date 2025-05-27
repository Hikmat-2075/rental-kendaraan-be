package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

//Paksi
@Entity
@DiscriminatorValue("penyewa")
public class Penyewa extends User {
    // Informasi tambahan (bisa ditambahkan ke form registrasi)
    private String noTelepon;
    private String alamat;

    // Relasi ke transaksi
    @OneToMany(mappedBy = "penyewa", cascade = CascadeType.ALL)
    private List<Transaksi> transaksiList;

    // Constructor dengan field tambahan
    public Penyewa(String name, String email, String password, String noTelepon, String alamat) {
        super(); // role default = penyewa
        this.noTelepon = noTelepon;
        this.alamat = alamat;
    }

    // Tambahan method (jika ingin logika dasar di entity)
    public void tambahTransaksi(Transaksi transaksi) {
        transaksi.setPenyewa(this);
        this.transaksiList.add(transaksi);
    }
}
