package com.kelompok3.rental_kendaraan_be.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kelompok3.rental_kendaraan_be.model.Kendaraan;
import com.kelompok3.rental_kendaraan_be.model.Pembayaran;
import com.kelompok3.rental_kendaraan_be.model.Pembayaran.StatusPembayaran;
import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.model.Transaksi.StatusTransaksi;
import com.kelompok3.rental_kendaraan_be.model.Kendaraan.StatusKendaraan;
import com.kelompok3.rental_kendaraan_be.repository.KendaraanRepository;
import com.kelompok3.rental_kendaraan_be.repository.PembayaranRepository;
import com.kelompok3.rental_kendaraan_be.repository.TransaksiRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PembayaranService {

    private final PembayaranRepository pembayaranRepository;
    private final TransaksiRepository transaksiRepository;
    private final KendaraanRepository kendaraanRepository;

    public PembayaranService(PembayaranRepository pembayaranRepository, TransaksiRepository transaksiRepository,
                             KendaraanRepository kendaraanRepository) {
        this.pembayaranRepository = pembayaranRepository;
        this.transaksiRepository = transaksiRepository;
        this.kendaraanRepository = kendaraanRepository;
    }

    // CREATE: Membuat pembayaran baru
    public Pembayaran buatPembayaranBaru(Long transaksiId, Double jumlahPembayaran) {
        // Ambil transaksi yang sesuai
        Transaksi transaksi = transaksiRepository.findById(transaksiId)
                .orElseThrow(() -> new EntityNotFoundException("Transaksi tidak ditemukan"));

        // Mengecek jumlah yang dibayar
        double totalHarga = transaksi.getTotalHarga();

        Pembayaran pembayaran;
        double kelebihanPembayaran = 0;

        if (jumlahPembayaran < totalHarga) {
            // Pembayaran kurang, status masih pending
            pembayaran = new Pembayaran(transaksi, "Manual", jumlahPembayaran, StatusPembayaran.PENDING, LocalDateTime.now());
        } else if (jumlahPembayaran > totalHarga) {
            // Pembayaran lebih, bisa dianggap kelebihan dan dicatat
            kelebihanPembayaran = jumlahPembayaran - totalHarga;
            pembayaran = new Pembayaran(transaksi, "Manual", jumlahPembayaran, StatusPembayaran.LUNAS, LocalDateTime.now());
        } else {
            // Pembayaran pas, langsung lunas
            pembayaran = new Pembayaran(transaksi, "Manual", jumlahPembayaran, StatusPembayaran.LUNAS, LocalDateTime.now());
        }

        // Simpan pembayaran
        pembayaranRepository.save(pembayaran);

        // Setelah pembayaran diproses, update status transaksi dan kendaraan
        if (pembayaran.getStatusPembayaran() == StatusPembayaran.LUNAS) {
            transaksi.setStatusTransaksi(StatusTransaksi.SELESAI);
            Kendaraan kendaraan = transaksi.getKendaraan();
            kendaraan.setStatus(StatusKendaraan.TERSEDIA); // Kendaraan kembali tersedia
            transaksiRepository.save(transaksi);
            kendaraanRepository.save(kendaraan);
        }

        return pembayaran;
    }

    // READ: Mendapatkan pembayaran berdasarkan ID
    public Pembayaran getPembayaranById(Long id) {
        return pembayaranRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pembayaran tidak ditemukan"));
    }

    // READ: Mendapatkan semua pembayaran
    public List<Pembayaran> getAllPembayaran() {
        return pembayaranRepository.findAll();
    }

    // UPDATE: Mengubah status pembayaran, jika diperlukan (misal update status jika pembayaran berhasil)
    public Pembayaran updateStatusPembayaran(Long id, StatusPembayaran statusPembayaran) {
        Pembayaran pembayaran = getPembayaranById(id);

        // Pastikan status pembayaran valid
        if (statusPembayaran != pembayaran.getStatusPembayaran()) {
            pembayaran.setStatusPembayaran(statusPembayaran);
            pembayaranRepository.save(pembayaran);

            // Jika statusnya LUNAS, update transaksi dan kendaraan
            if (statusPembayaran == StatusPembayaran.LUNAS) {
                Transaksi transaksi = pembayaran.getTransaksi();
                transaksi.setStatusTransaksi(StatusTransaksi.SELESAI);
                Kendaraan kendaraan = transaksi.getKendaraan();
                kendaraan.setStatus(StatusKendaraan.TERSEDIA);
                transaksiRepository.save(transaksi);
                kendaraanRepository.save(kendaraan);
            }
        }

        return pembayaran;
    }

    // DELETE: Menghapus pembayaran berdasarkan ID
    public void deletePembayaran(Long id) {
        Pembayaran pembayaran = getPembayaranById(id);
        pembayaranRepository.delete(pembayaran);
    }
}
