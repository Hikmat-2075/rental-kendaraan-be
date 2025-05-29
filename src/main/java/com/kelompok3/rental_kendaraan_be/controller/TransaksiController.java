package com.kelompok3.rental_kendaraan_be.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.service.TransaksiService;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    private final TransaksiService transaksiService;

    public TransaksiController(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<Transaksi>> getAll() {
        return ResponseEntity.ok(transaksiService.getAll());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaksi> getById(@PathVariable Long id) {
        return ResponseEntity.ok(transaksiService.getById(id));
    }

    // POST buat transaksi baru (penyewaan)
    @PostMapping
    public ResponseEntity<Transaksi> buatTransaksiBaru(@RequestBody Transaksi transaksi) {
        Transaksi created = transaksiService.buatTransaksiBaru(transaksi);
        return ResponseEntity.ok(created);
    }

    // PUT selesaikan transaksi
    @PutMapping("/{id}/selesai")
    public ResponseEntity<Transaksi> selesaikanTransaksi(@PathVariable Long id) {
        Transaksi updated = transaksiService.selesaikanTransaksi(id);
        return ResponseEntity.ok(updated);
    }

    // DELETE transaksi
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transaksiService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
