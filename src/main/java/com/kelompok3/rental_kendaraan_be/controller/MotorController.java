package com.kelompok3.rental_kendaraan_be.controller;

import com.kelompok3.rental_kendaraan_be.model.Motor;
import com.kelompok3.rental_kendaraan_be.service.MotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motor")
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping
    public Motor createMotor(@RequestBody Motor motor) {
        return motorService.saveMotor(motor);
    }

    @PutMapping("/{id}")
    public Motor updateMotor(@PathVariable Long id, @RequestBody Motor motor) {
        return motorService.updateMotor(id, motor);
    }

    @DeleteMapping("/{id}")
    public void deleteMotor(@PathVariable Long id) {
        motorService.deleteMotor(id);
    }
}
