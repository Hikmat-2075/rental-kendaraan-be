package com.kelompok3.rental_kendaraan_be.service;

import com.kelompok3.rental_kendaraan_be.model.Mobil;
import com.kelompok3.rental_kendaraan_be.repository.MobilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobilService {

    @Autowired
    private MobilRepository mobilRepository;

    public List<Mobil> getAllMobil() {
        return mobilRepository.findAll();
    }

    public Optional<Mobil> getMobilById(Long id) {
        return mobilRepository.findById(id);
    }

    public Mobil saveMobil(Mobil mobil) {
        return mobilRepository.save(mobil);
    }

    public Mobil updateMobil(Long id, Mobil mobil) {
        Optional<Mobil> existing = mobilRepository.findById(id);
        if (existing.isPresent()) {
            Mobil m = existing.get();
            m.setTipeMobil(mobil.getTipeMobil());
            m.setKapasitas(mobil.getKapasitas());
            m.setNama(mobil.getNama());
            m.setJenis(mobil.getJenis());
            m.setNomorPolisi(mobil.getNomorPolisi());
            m.setTahun(mobil.getTahun());
            m.setStatus(mobil.getStatus());
            m.setHarga(mobil.getHarga());
            m.setJenisTransmisi(mobil.getJenisTransmisi());
            m.setJenisBahanBakar(mobil.getJenisBahanBakar());
            return mobilRepository.save(m);
        } else {
            return null;
        }
    }

    public void deleteMobil(Long id) {
        mobilRepository.deleteById(id);
    }
}
