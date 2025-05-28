package com.kelompok3.rental_kendaraan_be.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Transaksi transaksi;

    @Column(nullable = false)
    private String metodePembayaran; // QRIS, Transfer, Debit

    @Column(nullable = false)
    private String status; // MENUNGGU_KONFIRMASI, BERHASIL

    @Column(nullable = false)
    private LocalDateTime waktuPembayaran;

    @Column(nullable = false)
    private LocalDateTime waktuKonfirmasi;

    public Pembayaran() {
    }

    // Getters and Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getWaktuPembayaran() {
        return waktuPembayaran;
    }

    public void setWaktuPembayaran(LocalDateTime waktuPembayaran) {
        this.waktuPembayaran = waktuPembayaran;
    }

    public LocalDateTime getWaktuKonfirmasi() {
        return waktuKonfirmasi;
    }

    public void setWaktuKonfirmasi(LocalDateTime waktuKonfirmasi) {
        this.waktuKonfirmasi = waktuKonfirmasi;
    }
}

