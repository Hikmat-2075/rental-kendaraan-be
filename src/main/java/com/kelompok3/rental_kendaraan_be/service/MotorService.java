package com.kelompok3.rental_kendaraan_be.service;

import com.kelompok3.rental_kendaraan_be.model.Motor;
import com.kelompok3.rental_kendaraan_be.repository.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorService {

    @Autowired
    private MotorRepository motorRepository;

    public List<Motor> getAllMotor() {
        return motorRepository.findAll();
    }

    public Optional<Motor> getMotorById(Long id) {
        return motorRepository.findById(id);
    }

    public Motor saveMotor(Motor motor) {
        return motorRepository.save(motor);
    }

    public Motor updateMotor(Long id, Motor motor) {
        Optional<Motor> existing = motorRepository.findById(id);
        if (existing.isPresent()) {
            Motor m = existing.get();
            m.setTipeMotor(motor.getTipeMotor());
            m.setKapasitasMesin(motor.getKapasitasMesin());
            m.setNama(motor.getNama());
            m.setJenis(motor.getJenis());
            m.setNomorPolisi(motor.getNomorPolisi());
            m.setTahun(motor.getTahun());
            m.setStatus(motor.getStatus());
            m.setHarga(motor.getHarga());
            m.setJenisTransmisi(motor.getJenisTransmisi());
            m.setJenisBahanBakar(motor.getJenisBahanBakar());
            return motorRepository.save(m);
        } else {
            return null;
        }
    }

    public void deleteMotor(Long id) {
        motorRepository.deleteById(id);
    }
}
