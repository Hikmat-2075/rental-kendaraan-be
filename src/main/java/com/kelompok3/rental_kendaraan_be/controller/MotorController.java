package com.kelompok3.rental_kendaraan_be.controller;

import com.kelompok3.rental_kendaraan_be.model.Motor;
import com.kelompok3.rental_kendaraan_be.service.MotorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

import com.kelompok3.rental_kendaraan_be.model.Kendaraan;

@RestController
@RequestMapping("/api/motor")
public class MotorController {

    @Autowired
    private MotorService motorService;

    @GetMapping
    public List<Motor> getAllMotor() {
        return motorService.getAllMotor();
    }

    @GetMapping("/{id}")
    public Optional<Motor> getMotorById(@PathVariable Long id) {
        return motorService.getMotorById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Motor> createMotor(
            @RequestParam("gambar") MultipartFile file,
            @RequestParam("nama") String nama,
            @RequestParam("nomorPolisi") String nomorPolisi,
            @RequestParam("tahun") int tahun,
            @RequestParam("status") String status,
            @RequestParam("harga") int harga,
            @RequestParam("jenisTransmisi") String jenisTransmisi,
            @RequestParam("jenisBahanBakar") String jenisBahanBakar,
            @RequestParam("tipeMotor") String tipeMotor,
            @RequestParam("kapasitasMesin") Integer kapasitasMesin) {

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = "public/uploads/motor/" + fileName;
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            Motor motor = new Motor();
            motor.setNama(nama);
            motor.setJenis("Motor");
            motor.setNomorPolisi(nomorPolisi);
            motor.setTahun(tahun);
            motor.setStatus(Kendaraan.StatusKendaraan.valueOf(status));
            motor.setHarga((double) harga);
            motor.setJenisTransmisi(jenisTransmisi);
            motor.setJenisBahanBakar(jenisBahanBakar);
            motor.setTipeMotor(tipeMotor);
            motor.setKapasitasMesin(kapasitasMesin);
            motor.setGambar("/uploads/motor/" + fileName);

            Motor saved = motorService.saveMotor(motor);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Motor> updateMotor(
            @PathVariable Long id,
            @RequestParam(value = "gambar", required = false) MultipartFile file,
            @RequestParam(value = "nama", required = false) String nama,
            @RequestParam(value = "nomorPolisi", required = false) String nomorPolisi,
            @RequestParam(value = "tahun", required = false) Integer tahun,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "harga", required = false) Integer harga,
            @RequestParam(value = "jenisTransmisi", required = false) String jenisTransmisi,
            @RequestParam(value = "jenisBahanBakar", required = false) String jenisBahanBakar,
            @RequestParam(value = "tipeMotor", required = false) String tipeMotor,
            @RequestParam(value = "kapasitasMesin", required = false) Integer kapasitasMesin) {

        try {
            Motor existing = motorService.getMotorById(id)
                    .orElseThrow(() -> new RuntimeException("Motor tidak ditemukan"));

            if (file != null && !file.isEmpty()) {
                if (existing.getGambar() != null && !existing.getGambar().isEmpty()) {
                    String oldPathStr = "public" + existing.getGambar();
                    Path oldPath = Paths.get(oldPathStr);
                    Files.deleteIfExists(oldPath);
                }

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String filePath = "public/uploads/motor/" + fileName;
                Path path = Paths.get(filePath);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                existing.setGambar("/uploads/motor/" + fileName);
            }

            if (nama != null) existing.setNama(nama);
            if (nomorPolisi != null) existing.setNomorPolisi(nomorPolisi);
            if (tahun != null) existing.setTahun(tahun);
            if (status != null) existing.setStatus(Kendaraan.StatusKendaraan.valueOf(status));
            if (harga != null) existing.setHarga((double) harga);
            if (jenisTransmisi != null) existing.setJenisTransmisi(jenisTransmisi);
            if (jenisBahanBakar != null) existing.setJenisBahanBakar(jenisBahanBakar);
            if (tipeMotor != null) existing.setTipeMotor(tipeMotor);
            if (kapasitasMesin != null) existing.setKapasitasMesin(kapasitasMesin);

            Motor updated = motorService.saveMotor(existing);
            return ResponseEntity.ok(updated);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMotor(@PathVariable Long id) {
        Optional<Motor> motor = motorService.getMotorById(id);
        if (motor.isPresent()) {
            if (motor.get().getGambar() != null && !motor.get().getGambar().isEmpty()) {
                String oldPathStr = "public" + motor.get().getGambar();
                try {
                    Files.deleteIfExists(Paths.get(oldPathStr));
                } catch (IOException ignored) {}
            }
            motorService.deleteMotor(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
