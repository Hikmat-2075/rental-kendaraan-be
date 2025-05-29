package com.kelompok3.rental_kendaraan_be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kelompok3.rental_kendaraan_be.model.Pembayaran;
import com.kelompok3.rental_kendaraan_be.service.PembayaranService;
import com.kelompok3.rental_kendaraan_be.model.Pembayaran.StatusPembayaran;

@RestController
@RequestMapping("/api/pembayaran")
public class PembayaranController {

    private final PembayaranService pembayaranService;

    public PembayaranController(PembayaranService pembayaranService) {
        this.pembayaranService = pembayaranService;
    }

    // Membuat pembayaran baru
    @PostMapping("/buat/{transaksiId}")
    public ResponseEntity<Pembayaran> buatPembayaran(@PathVariable Long transaksiId, 
                                                     @RequestParam Double jumlahPembayaran) {
        Pembayaran pembayaran = pembayaranService.buatPembayaranBaru(transaksiId, jumlahPembayaran);

        // Cek apakah ada kelebihan pembayaran
        if (pembayaran.getJumlahPembayaran() > pembayaran.getTransaksi().getTotalHarga()) {
            double kelebihanPembayaran = pembayaran.getJumlahPembayaran() - pembayaran.getTransaksi().getTotalHarga();
            // Kirimkan informasi kelebihan pembayaran ke frontend jika ada
            return ResponseEntity.ok()
                    .header("Kelebihan-Pembayaran", String.valueOf(kelebihanPembayaran))
                    .body(pembayaran);
        }

        return ResponseEntity.ok(pembayaran);
    }

    // Mendapatkan pembayaran berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Pembayaran> getPembayaranById(@PathVariable Long id) {
        Pembayaran pembayaran = pembayaranService.getPembayaranById(id);
        return ResponseEntity.ok(pembayaran);
    }

    // Mendapatkan semua pembayaran
    @GetMapping
    public ResponseEntity<?> getAllPembayaran() {
        return ResponseEntity.ok(pembayaranService.getAllPembayaran());
    }

    // Mengupdate status pembayaran
    @PutMapping("/update/{id}")
    public ResponseEntity<Pembayaran> updateStatusPembayaran(@PathVariable Long id, 
                                                             @RequestParam StatusPembayaran statusPembayaran) {
        Pembayaran pembayaran = pembayaranService.updateStatusPembayaran(id, statusPembayaran);
        return ResponseEntity.ok(pembayaran);
    }

    // Menghapus pembayaran berdasarkan ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePembayaran(@PathVariable Long id) {
        pembayaranService.deletePembayaran(id);
        return ResponseEntity.noContent().build();
    }
}
