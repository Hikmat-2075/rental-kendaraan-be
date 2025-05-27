package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

//Bram
@Entity
@Table(name = "motor")
public class Motor extends Kendaraan {

    @Column(name = "tipe_motor")
    private String tipeMotor;

    @Column(name = "kapasitas_mesin")
    private int kapasitasMesin;

    public Motor() {
        super();
    }

    public Motor(String nama, String jenis, String nomorPolisi, Integer tahun, String status, Double harga, String jenisTransmisi, String jenisBahanBakar, String tipeMotor, int kapasitasMesin) {
        super(nama, jenis, nomorPolisi, tahun, status, harga, jenisTransmisi, jenisBahanBakar);
        this.tipeMotor = tipeMotor;
        this.kapasitasMesin = kapasitasMesin;
    }

    public String getTipeMotor() {
        return tipeMotor;
    }

    public void setTipeMotor(String tipeMotor) {
        this.tipeMotor = tipeMotor;
    }

    public int getKapasitasMesin() {
        return kapasitasMesin;
    }

    public void setKapasitasMesin(int kapasitasMesin) {
        this.kapasitasMesin = kapasitasMesin;
    }
}
