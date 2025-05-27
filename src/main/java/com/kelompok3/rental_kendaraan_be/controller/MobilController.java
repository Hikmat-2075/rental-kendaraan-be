package com.kelompok3.rental_kendaraan_be.controller;

import com.kelompok3.rental_kendaraan_be.model.Mobil;
import com.kelompok3.rental_kendaraan_be.service.MobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mobil")
@CrossOrigin(origins = "http://localhost:3000")
public class MobilController {

    @Autowired
    private MobilService mobilService;

    @GetMapping
    public List<Mobil> getAllMobil() {
        return mobilService.getAllMobil();
    }

    @GetMapping("/{id}")
    public Optional<Mobil> getMobilById(@PathVariable Long id) {
        return mobilService.getMobilById(id);
    }

    @PostMapping
    public Mobil createMobil(@RequestBody Mobil mobil) {
        return mobilService.saveMobil(mobil);
    }

    @PutMapping("/{id}")
    public Mobil updateMobil(@PathVariable Long id, @RequestBody Mobil mobil) {
        return mobilService.updateMobil(id, mobil);
    }

    @DeleteMapping("/{id}")
    public void deleteMobil(@PathVariable Long id) {
        mobilService.deleteMobil(id);
    }
}
