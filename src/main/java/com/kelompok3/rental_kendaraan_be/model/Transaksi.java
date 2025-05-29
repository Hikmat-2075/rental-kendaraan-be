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
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User penyewa;  // Menghubungkan dengan User (Penyewa)

    @ManyToOne
    @JoinColumn(name = "kendaraan_id", nullable = false)
    private Kendaraan kendaraan;  // Menghubungkan dengan Kendaraan (Mobil atau Motor)

    @Column(name = "tanggal_peminjaman", nullable = false)
    private LocalDateTime tanggalPeminjaman;

    @Column(name = "tanggal_pengembalian", nullable = false)
    private LocalDateTime tanggalPengembalian;

    @Column(name = "total_harga", nullable = false)
    private Double totalHarga;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_transaksi", nullable = false)
    private StatusTransaksi statusTransaksi;  // Status transaksi: PENDING, SEDANG_SEWA, SELESAI

    @Column(name = "metode_pembayaran", nullable = false)
    private String metodePembayaran; // Metode Pembayaran (QRIS, Debit, dll)

    // Constructors
    public Transaksi() {
    }

    public Transaksi(User penyewa, Kendaraan kendaraan, LocalDateTime tanggalPeminjaman, LocalDateTime tanggalPengembalian, Double totalHarga, StatusTransaksi statusTransaksi, String metodePembayaran) {
        this.penyewa = penyewa;
        this.kendaraan = kendaraan;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.tanggalPengembalian = tanggalPengembalian;
        this.totalHarga = totalHarga;
        this.statusTransaksi = statusTransaksi;
        this.metodePembayaran = metodePembayaran;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPenyewa() {
        return penyewa;
    }

    public void setPenyewa(User penyewa) {
        this.penyewa = penyewa;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }

    public LocalDateTime getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(LocalDateTime tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public LocalDateTime getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(LocalDateTime tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public Double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public StatusTransaksi getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(StatusTransaksi statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public enum StatusTransaksi {
        PENDING,
        SEDANG_SEWA,
        SELESAI
    }
}
