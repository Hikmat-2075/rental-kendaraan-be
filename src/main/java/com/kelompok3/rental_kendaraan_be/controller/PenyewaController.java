package com.kelompok3.rental_kendaraan_be.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok3.rental_kendaraan_be.model.Kendaraan;
import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.service.PenyewaService;


//Paksi
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class PenyewaController {
    @Autowired
    private PenyewaService penyewaService;

    // 1. Lihat semua kendaraan
    @GetMapping("/kendaraan")
    public ResponseEntity<List<Kendaraan>> getSemuaKendaraan() {
        return ResponseEntity.ok(penyewaService.lihatSemuaKendaraan());
    }

    // 2. Lihat kendaraan tersedia
    @GetMapping("/kendaraan/tersedia")
    public ResponseEntity<List<Kendaraan>> getKendaraanTersedia() {
        return ResponseEntity.ok(penyewaService.lihatKendaraanTersedia());
    }

    // 3. Pesan kendaraan
    @PostMapping("/pesan")
    public ResponseEntity<Transaksi> pesanKendaraan(
            @RequestParam Long penyewaId,
            @RequestParam Long kendaraanId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate mulai,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate akhir
    ) {
        Transaksi transaksi = penyewaService.pesanKendaraan(penyewaId, kendaraanId, mulai, akhir);
        return ResponseEntity.ok(transaksi);
    }

    // 4. Lihat riwayat transaksi penyewa
    @GetMapping("/transaksi/{penyewaId}")
    public ResponseEntity<List<Transaksi>> riwayatTransaksi(@PathVariable Long penyewaId) {
        return ResponseEntity.ok(penyewaService.lihatRiwayatTransaksi(penyewaId));
    }

    // 5. Batalkan transaksi
    @PostMapping("/transaksi/{transaksiId}/batal")
    public ResponseEntity<String> batalkanTransaksi(
            @PathVariable Long transaksiId,
            @RequestParam Long penyewaId
    ) {
        penyewaService.batalkanTransaksi(transaksiId, penyewaId);
        return ResponseEntity.ok("Transaksi berhasil dibatalkan");
    }
}
