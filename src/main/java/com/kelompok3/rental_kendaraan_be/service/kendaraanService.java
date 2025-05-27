package com.kelompok3.rental_kendaraan_be.service;

import com.kelompok3.rental_kendaraan_be.model.Kendaraan;
import com.kelompok3.rental_kendaraan_be.repository.KendaraanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class kendaraanService {

    @Autowired
    private KendaraanRepository kendaraanRepository;

    public List<Kendaraan> getAllKendaraan() {
        return kendaraanRepository.findAll();
    }

    public Optional<Kendaraan> getKendaraanById(Long id) {
        return kendaraanRepository.findById(id);
    }

    public Kendaraan saveKendaraan(Kendaraan kendaraan) {
        return kendaraanRepository.save(kendaraan);
    }

    public Kendaraan updateKendaraan(Long id, Kendaraan kendaraan) {
        Optional<Kendaraan> existing = kendaraanRepository.findById(id);
        if (existing.isPresent()) {
            Kendaraan k = existing.get();
            k.setNama(kendaraan.getNama());
            k.setJenis(kendaraan.getJenis());
            k.setNomorPolisi(kendaraan.getNomorPolisi());
            k.setTahun(kendaraan.getTahun());
            k.setStatus(kendaraan.getStatus());
            k.setHarga(kendaraan.getHarga());
            k.setJenisTransmisi(kendaraan.getJenisTransmisi());
            k.setJenisBahanBakar(kendaraan.getJenisBahanBakar()); // tambahkan baris ini
            return kendaraanRepository.save(k);
        } else {
            return null;
        }
    }

    public void deleteKendaraan(Long id) {
        kendaraanRepository.deleteById(id);
    }
}
