package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

//Bram
@Entity
@Table(name = "mobil")
public class Mobil extends Kendaraan {

    @Column(name = "tipe_mobil")
    private String tipeMobil;

    @Column(name = "kapasitas")
    private int kapasitas;

    public String getTipeMobil() {
        return tipeMobil;
    }

    public void setTipeMobil(String tipeMobil) {
        this.tipeMobil = tipeMobil;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    // Constructor tanpa parameter (default)
    public Mobil() {
        super();
    }

    // Constructor dengan semua field (termasuk field parent)
    public Mobil(String nama, String jenis, String nomorPolisi, Integer tahun, String status, Double harga, String jenisTransmisi, String jenisBahanBakar, String tipeMobil, int kapasitas) {
        super(nama, jenis, nomorPolisi, tahun, status, harga, jenisTransmisi, jenisBahanBakar);
        this.tipeMobil = tipeMobil;
        this.kapasitas = kapasitas;
    }

    // Tambahkan getter dan setter lainnya jika diperlukan

}
