package com.kelompok3.rental_kendaraan_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.service.TransaksiService;

import java.util.List;

@RestController
@RequestMapping("/api/transaksi")
@CrossOrigin(origins = "http://localhost:3000")
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;

    /**
     * //"fitur buat transaksi"
     * @param transaksi 
     * @return 
     */
    @PostMapping("/buat")
    public Transaksi buatTransaksi(@RequestBody Transaksi transaksi) {
        return transaksiService.buatTransaksi(transaksi);
    }

    /**
     * "riwayat peruser"
     * @param userId 
     * @return 
     */
    @GetMapping("/riwayat/{userId}")
    public List<Transaksi> getRiwayatTransaksiPerUser(@PathVariable Long userId) {
        return transaksiService.getRiwayatTransaksiPerUser(userId);
    }

    /**status trasnaksi
     * 
     * @param id
     * @param status 
     * @return 
     */
    @PutMapping("/update-status/{id}")
    public Transaksi updateStatusTransaksi(@PathVariable Long id, @RequestParam String status) {
        return transaksiService.updateStatusTransaksi(id, status);
    }

    //logika peminjaman dan pengembalian ada di service
}
