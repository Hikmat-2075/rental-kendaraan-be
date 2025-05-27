package com.kelompok3.rental_kendaraan_be.service;

import com.kelompok3.rental_kendaraan_be.model.Kendaraan;
import com.kelompok3.rental_kendaraan_be.model.Penyewa;
import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.repository.KendaraanRepository;
import com.kelompok3.rental_kendaraan_be.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
//paksi
@Service
public class PenyewaService {

    @Autowired
    private KendaraanRepository kendaraanRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private PenyewaRepository penyewaRepository;

    // Melihat semua kendaraan
    public List<Kendaraan> lihatSemuaKendaraan() {
        return kendaraanRepository.findAll();
    }

    // Melihat kendaraan yang tersedia saja
    public List<Kendaraan> lihatKendaraanTersedia() {
        return kendaraanRepository.findByTersedia(true);
    }

    // Melihat riwayat transaksi penyewa
    public List<Transaksi> lihatRiwayatTransaksi(Long penyewaId) {
        return transaksiRepository.findByPenyewaId(penyewaId);
    }

    public Transaksi pesanKendaraan(Long penyewaId, Long kendaraanId, LocalDate mulai, LocalDate akhir) {
        // validasi dan proses simpan
        Penyewa penyewa = penyewaRepository.findById(penyewaId).orElseThrow(() -> new RuntimeException("Penyewa tidak ditemukan"));
        Kendaraan kendaraan = kendaraanRepository.findById(kendaraanId).orElseThrow(() -> new RuntimeException("Kendaraan tidak ditemukan"));

        Transaksi transaksi = new Transaksi();
        transaksi.setPenyewa(penyewa);
        transaksi.setKendaraan(kendaraan);
        transaksi.setTanggalMulai(mulai);
        transaksi.setTanggalAkhir(akhir);
        transaksi.setStatus("PENDING");

        return transaksiRepository.save(transaksi);
    }
    }

    public void batalkanTransaksi(Long transaksiId, Long penyewaId) {
        Transaksi transaksi = transaksiRepository.findById(transaksiId).orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan"));
        if (!transaksi.getPenyewa().getId().equals(penyewaId)) {
            throw new RuntimeException("Tidak boleh membatalkan transaksi orang lain!");
        }
        transaksi.setStatus("DIBATALKAN");
        transaksiRepository.save(transaksi);
    }
    }
}
