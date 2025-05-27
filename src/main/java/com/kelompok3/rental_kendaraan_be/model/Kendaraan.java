package com.kelompok3.rental_kendaraan_be.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kendaraan")
@Inheritance(strategy = InheritanceType.JOINED)
public class Kendaraan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "jenis", nullable = false)
    private String jenis;

    @Column(name = "nomor_polisi", nullable = false, unique = true)
    private String nomorPolisi;

    @Column(name = "tahun")
    private Integer tahun;

    @Column(name = "status")
    private String status;

    @Column(name = "harga")
    private Double harga;

    @Column(name = "jenis_transmisi")
    private String jenisTransmisi;

    @Column(name = "jenis_bahan_bakar")
    private String jenisBahanBakar;

    // Constructor tanpa parameter (default)
    public Kendaraan() {
    }

    // Constructor dengan semua field (kecuali id, karena auto-generated)
    public Kendaraan(String nama, String jenis, String nomorPolisi, Integer tahun, String status, Double harga, String jenisTransmisi, String jenisBahanBakar) {
        this.nama = nama;
        this.jenis = jenis;
        this.nomorPolisi = nomorPolisi;
        this.tahun = tahun;
        this.status = status;
        this.harga = harga;
        this.jenisTransmisi = jenisTransmisi;
        this.jenisBahanBakar = jenisBahanBakar;
    }

    // Getter dan Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNomorPolisi() {
        return nomorPolisi;
    }

    public void setNomorPolisi(String nomorPolisi) {
        this.nomorPolisi = nomorPolisi;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public String getJenisTransmisi() {
        return jenisTransmisi;
    }

    public void setJenisTransmisi(String jenisTransmisi) {
        this.jenisTransmisi = jenisTransmisi;
    }

    public String getJenisBahanBakar() {
        return jenisBahanBakar;
    }

    public void setJenisBahanBakar(String jenisBahanBakar) {
        this.jenisBahanBakar = jenisBahanBakar;
    }
}
