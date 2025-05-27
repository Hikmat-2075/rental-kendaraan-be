package com.kelompok3.rental_kendaraan_be.controller;

import com.kelompok3.rental_kendaraan_be.model.Kendaraan;
import com.kelompok3.rental_kendaraan_be.service.kendaraanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kendaraan")
@CrossOrigin(origins = "http://localhost:3000")
public class KendaraanController {

    @Autowired
    private kendaraanService kendaraanService;

    @GetMapping
    public List<Kendaraan> getAllKendaraan() {
        return kendaraanService.getAllKendaraan();
    }

    @GetMapping("/{id}")
    public Optional<Kendaraan> getKendaraanById(@PathVariable Long id) {
        return kendaraanService.getKendaraanById(id);
    }

    @PostMapping
    public Kendaraan createKendaraan(@RequestBody Kendaraan kendaraan) {
        return kendaraanService.saveKendaraan(kendaraan);
    }

    @PutMapping("/{id}")
    public Kendaraan updateKendaraan(@PathVariable Long id, @RequestBody Kendaraan kendaraan) {
        return kendaraanService.updateKendaraan(id, kendaraan);
    }

    @DeleteMapping("/{id}")
    public void deleteKendaraan(@PathVariable Long id) {
        kendaraanService.deleteKendaraan(id);
    }
}
