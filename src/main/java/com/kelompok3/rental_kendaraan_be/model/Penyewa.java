package com.kelompok3.rental_kendaraan_be.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

//Paksi
@Entity
@DiscriminatorValue("PENYEWA")
public class Penyewa extends User {

    @Column
    private String noTelepon;

    @Column
    private String alamat;

    // Relasi ke transaksi
    @OneToMany(mappedBy = "penyewa", cascade = CascadeType.ALL)
    private List<Transaksi> transaksiList = new ArrayList<>();

    // Default constructor (wajib untuk JPA)
    public Penyewa() {
        super();
        this.setRole("PENYEWA");
    }

    // Constructor lengkap
    public Penyewa(String username, String password, String email, String namaLengkap, String noTelepon, String alamat, LocalDateTime createdAt) {
        super(username, password, email, namaLengkap, "PENYEWA", createdAt);
        this.noTelepon = noTelepon;
        this.alamat = alamat;
    }

    // Getter & Setter
    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public List<Transaksi> getTransaksiList() {
        return transaksiList;
    }

    public void setTransaksiList(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

}
