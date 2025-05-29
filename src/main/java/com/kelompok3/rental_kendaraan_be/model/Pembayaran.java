package com.kelompok3.rental_kendaraan_be.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaksi_id", nullable = false)
    private Transaksi transaksi; // Menghubungkan dengan Transaksi

    @Column(name = "metode_pembayaran", nullable = false)
    private String metodePembayaran; // QRIS, Debit, dll

    @Column(name = "jumlah_pembayaran", nullable = false)
    private Double jumlahPembayaran; // Jumlah uang yang dibayarkan

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pembayaran", nullable = false)
    private StatusPembayaran statusPembayaran; // STATUS: PENDING, LUNAS, GAGAL

    @Column(name = "tanggal_pembayaran", nullable = false)
    private LocalDateTime tanggalPembayaran; // Tanggal pembayaran dilakukan

    // Constructor tanpa parameter (default)
    public Pembayaran() {
    }

    // Constructor dengan semua field
    public Pembayaran(Transaksi transaksi, String metodePembayaran, Double jumlahPembayaran, StatusPembayaran statusPembayaran, LocalDateTime tanggalPembayaran) {
        this.transaksi = transaksi;
        this.metodePembayaran = metodePembayaran;
        this.jumlahPembayaran = jumlahPembayaran;
        this.statusPembayaran = statusPembayaran;
        this.tanggalPembayaran = tanggalPembayaran;
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public Double getJumlahPembayaran() {
        return jumlahPembayaran;
    }

    public void setJumlahPembayaran(Double jumlahPembayaran) {
        this.jumlahPembayaran = jumlahPembayaran;
    }

    public StatusPembayaran getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(StatusPembayaran statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public LocalDateTime getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(LocalDateTime tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public enum StatusPembayaran {
        PENDING, // Pembayaran belum selesai
        LUNAS,   // Pembayaran berhasil dan selesai
        GAGAL    // Pembayaran gagal
    }
}
