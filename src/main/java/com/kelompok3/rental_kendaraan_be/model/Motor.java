package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "motor")
public class Motor extends Kendaraan {

    @Column(name = "tipe_motor")
    private String tipeMotor;

    @Column(name = "kapasitas_mesin")
    private int kapasitasMesin;

    // Constructor tanpa parameter (default)
    public Motor() {
        super();
    }

    // Constructor dengan semua field (termasuk field dari superclass)
    public Motor(String nama, String jenis, String nomorPolisi, Integer tahun, StatusKendaraan status, Double harga, String jenisTransmisi, String jenisBahanBakar, String tipeMotor, int kapasitasMesin) {
        super(nama, jenis, nomorPolisi, tahun, status, harga, jenisTransmisi, jenisBahanBakar);
        this.tipeMotor = tipeMotor;
        this.setKapasitasMesin(kapasitasMesin);
    }

    // Getter dan Setter untuk tipeMotor
    public String getTipeMotor() {
        return tipeMotor;
    }

    public void setTipeMotor(String tipeMotor) {
        this.tipeMotor = tipeMotor;
    }

    // Getter dan Setter untuk kapasitasMesin
    public int getKapasitasMesin() {
        return kapasitasMesin;
    }

    public void setKapasitasMesin(int kapasitasMesin) {
        if (kapasitasMesin < 0) {
            throw new IllegalArgumentException("Kapasitas mesin tidak boleh negatif");
        }
        this.kapasitasMesin = kapasitasMesin;
    }
}
