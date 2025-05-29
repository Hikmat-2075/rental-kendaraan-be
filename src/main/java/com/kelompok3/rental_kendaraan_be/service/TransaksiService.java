package com.kelompok3.rental_kendaraan_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.repository.TransaksiRepository;
import java.util.List;
//raffa
@Service
public class TransaksiService {
    ///logika bisnis untuk mengelola transaksi kendaraan

    @Autowired
    private TransaksiRepository transaksiRepository;

    public Transaksi buatTransaksi(Transaksi transaksi) {
   
    transaksi.setStatusTransaksi(Transaksi.StatusTransaksi.SEDANG_SEWA);
    return transaksiRepository.save(transaksi);
    }

    public List<Transaksi> getRiwayatTransaksiPerUser(Long userId) {
        return transaksiRepository.findByUserId(userId);
    }

    public Transaksi updateStatusTransaksi(Long id, String status) {
        Transaksi transaksi = transaksiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan"));

        
        try {
            transaksi.setStatusTransaksi(Transaksi.StatusTransaksi.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status tidak valid: " + status);
        }

        return transaksiRepository.save(transaksi);
    }
}
