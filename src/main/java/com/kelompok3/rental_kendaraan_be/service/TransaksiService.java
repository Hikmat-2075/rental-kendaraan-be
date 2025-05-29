package com.kelompok3.rental_kendaraan_be.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kelompok3.rental_kendaraan_be.model.Kendaraan;
import com.kelompok3.rental_kendaraan_be.model.Kendaraan.StatusKendaraan;
import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.model.Transaksi.StatusTransaksi;
import com.kelompok3.rental_kendaraan_be.repository.KendaraanRepository;
import com.kelompok3.rental_kendaraan_be.repository.TransaksiRepository;

@Service
public class TransaksiService {

    private final TransaksiRepository transaksiRepository;
    private final KendaraanRepository kendaraanRepository;

    public TransaksiService(TransaksiRepository transaksiRepository, KendaraanRepository kendaraanRepository) {
        this.transaksiRepository = transaksiRepository;
        this.kendaraanRepository = kendaraanRepository;
    }

    public List<Transaksi> getAll() {
        return transaksiRepository.findAll();
    }

    public Transaksi getById(Long id) {
        return transaksiRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan"));
    }

    public Transaksi buatTransaksiBaru(Transaksi transaksi) {
        Kendaraan kendaraan = kendaraanRepository.findById(transaksi.getKendaraan().getId())
                .orElseThrow(() -> new RuntimeException("Kendaraan tidak ditemukan"));

        if (kendaraan.getStatus() != StatusKendaraan.TERSEDIA) {
            throw new RuntimeException("Kendaraan tidak tersedia");
        }

        kendaraan.setStatus(StatusKendaraan.DISEWA);
        transaksi.setStatusTransaksi(StatusTransaksi.SEDANG_SEWA);

        kendaraanRepository.save(kendaraan);
        return transaksiRepository.save(transaksi);
    }

    public Transaksi selesaikanTransaksi(Long id) {
        Transaksi transaksi = getById(id);

        if (transaksi.getStatusTransaksi() != StatusTransaksi.SEDANG_SEWA) {
            throw new RuntimeException("Transaksi belum dalam status sedang sewa");
        }

        transaksi.setStatusTransaksi(StatusTransaksi.SELESAI);

        Kendaraan kendaraan = transaksi.getKendaraan();
        kendaraan.setStatus(StatusKendaraan.TERSEDIA);

        kendaraanRepository.save(kendaraan);
        return transaksiRepository.save(transaksi);
    }

    public void delete(Long id) {
        transaksiRepository.deleteById(id);
    }
}
