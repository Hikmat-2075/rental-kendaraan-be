package com.kelompok3.rental_kendaraan_be.service;

// import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.repository.TransaksiRepository;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Map;
// import java.util.HashMap;
//paksi
@Service
public class AdminService {

    private final TransaksiRepository transaksiRepository;

    // @Autowired
    // public AdminService(TransaksiRepository transaksiRepository) {
    //     this.transaksiRepository = transaksiRepository;
    // }

    // /**
    //  * Mendapatkan semua transaksi dengan status 'PENDING'
    //  */
    // public List<Transaksi> getTransaksiMenungguKonfirmasi() {
    //     return transaksiRepository.findByStatus("PENDING");
    // }

    // /**
    //  * Konfirmasi transaksi (ubah status jadi 'DIKONFIRMASI')
    //  */
    // public void konfirmasiTransaksi(Long id) {
    //     Transaksi transaksi = transaksiRepository.findById(id)
    //             .orElseThrow(() -> new InputValidationException("Transaksi tidak ditemukan"));
    //     transaksi.setStatus("DIKONFIRMASI");
    //     transaksiRepository.save(transaksi);
    // }

    // /**
    //  * Batalkan transaksi (ubah status jadi 'DIBATALKAN')
    //  */
    // public void batalkanTransaksi(Long id) {
    //     Transaksi transaksi = ((Object) transaksiRepository.findById(id))
    //             .orElseThrow(() -> new InputValidationException("Transaksi tidak ditemukan"));
    //     transaksi.setStatus("DIBATALKAN");
    //     transaksiRepository.save(transaksi);
    // }

    // /**
    //  * Mendapatkan data statistik dashboard
    //  */
    // public Map<String, Object> getStatistikDashboard() {
    //     Map<String, Object> data = new HashMap<>();

    //     long totalTransaksi = transaksiRepository.count();
    //     long transaksiPending = transaksiRepository.countByStatus("PENDING");
    //     long transaksiSelesai = transaksiRepository.countByStatus("SELESAI");

    //     data.put("totalTransaksi", totalTransaksi);
    //     data.put("transaksiPending", transaksiPending);
    //     data.put("transaksiSelesai", transaksiSelesai);

    //     return data;
    // }
}
