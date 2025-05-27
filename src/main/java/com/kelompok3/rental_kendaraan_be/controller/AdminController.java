package com.kelompok3.rental_kendaraan_be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok3.rental_kendaraan_be.model.Kendaraan;
import com.kelompok3.rental_kendaraan_be.model.Pembayaran;
import com.kelompok3.rental_kendaraan_be.model.Transaksi;
import com.kelompok3.rental_kendaraan_be.service.AdminService;

//Paksi
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
     @Autowired
    private AdminService adminService;
    
    // Admin Management
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {
            Admin savedAdmin = adminService.createAdmin(admin);
            return ResponseEntity.ok(savedAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequest loginRequest) {
        Optional<Admin> admin = adminService.loginAdmin(loginRequest.getUsername(), loginRequest.getPassword());
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username atau password salah");
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        try {
            Admin updatedAdmin = adminService.updateAdmin(id, adminDetails);
            return ResponseEntity.ok(updatedAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok("Admin berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Gagal menghapus admin");
        }
    }
    
    // Kendaraan Management
    @PostMapping("/kendaraan")
    public ResponseEntity<?> addKendaraan(@RequestBody Kendaraan kendaraan) {
        try {
            Kendaraan savedKendaraan = adminService.addKendaraan(kendaraan);
            return ResponseEntity.ok(savedKendaraan);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Gagal menambah kendaraan");
        }
    }
    
    @GetMapping("/kendaraan")
    public ResponseEntity<List<Kendaraan>> getAllKendaraan() {
        List<Kendaraan> kendaraanList = adminService.getAllKendaraan();
        return ResponseEntity.ok(kendaraanList);
    }
    
    @GetMapping("/kendaraan/{id}")
    public ResponseEntity<?> getKendaraanById(@PathVariable Long id) {
        Optional<Kendaraan> kendaraan = adminService.getKendaraanById(id);
        if (kendaraan.isPresent()) {
            return ResponseEntity.ok(kendaraan.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/kendaraan/{id}")
    public ResponseEntity<?> updateKendaraan(@PathVariable Long id, @RequestBody Kendaraan kendaraanDetails) {
        try {
            Kendaraan updatedKendaraan = adminService.updateKendaraan(id, kendaraanDetails);
            return ResponseEntity.ok(updatedKendaraan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/kendaraan/{id}")
    public ResponseEntity<?> deleteKendaraan(@PathVariable Long id) {
        try {
            adminService.deleteKendaraan(id);
            return ResponseEntity.ok("Kendaraan berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Gagal menghapus kendaraan");
        }
    }
    
    // Transaksi Management
    @GetMapping("/transaksi")
    public ResponseEntity<List<Transaksi>> getAllTransaksi() {
        List<Transaksi> transaksiList = adminService.getAllTransaksi();
        return ResponseEntity.ok(transaksiList);
    }
    
    @GetMapping("/transaksi/{id}")
    public ResponseEntity<?> getTransaksiById(@PathVariable Long id) {
        Optional<Transaksi> transaksi = adminService.getTransaksiById(id);
        if (transaksi.isPresent()) {
            return ResponseEntity.ok(transaksi.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/transaksi/{id}/status")
    public ResponseEntity<?> updateStatusTransaksi(@PathVariable Long id, @RequestBody UpdateStatusRequest request) {
        try {
            Transaksi updatedTransaksi = adminService.updateStatusTransaksi(id, request.getStatus());
            return ResponseEntity.ok(updatedTransaksi);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // Pembayaran Management
    @GetMapping("/pembayaran")
    public ResponseEntity<List<Pembayaran>> getAllPembayaran() {
        List<Pembayaran> pembayaranList = adminService.getAllPembayaran();
        return ResponseEntity.ok(pembayaranList);
    }
    
    @GetMapping("/pembayaran/{id}")
    public ResponseEntity<?> getPembayaranById(@PathVariable Long id) {
        Optional<Pembayaran> pembayaran = adminService.getPembayaranById(id);
        if (pembayaran.isPresent()) {
            return ResponseEntity.ok(pembayaran.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/pembayaran/{id}/status")
    public ResponseEntity<?> updateStatusPembayaran(@PathVariable Long id, @RequestBody UpdatePembayaranStatusRequest request) {
        try {
            Pembayaran updatedPembayaran = adminService.updateStatusPembayaran(id, request.getStatus());
            return ResponseEntity.ok(updatedPembayaran);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // // Inner classes for request bodies
    // public static class LoginRequest {
    //     private String username;
    //     private String password;
        
    //     public String getUsername() { return username; }
    //     public void setUsername(String username) { this.username = username; }
    //     public String getPassword() { return password; }
    //     public void setPassword(String password) { this.password = password; }
    // }
    
    // public static class UpdateStatusRequest {
    //     private Transaksi.StatusTransaksi status;
        
    //     public Transaksi.StatusTransaksi getStatus() { return status; }
    //     public void setStatus(Transaksi.StatusTransaksi status) { this.status = status; }
    // }
    
    // public static class UpdatePembayaranStatusRequest {
    //     private Pembayaran.StatusPembayaran status;
        
    //     public Pembayaran.StatusPembayaran getStatus() { return status; }
    //     public void setStatus(Pembayaran.StatusPembayaran status) { this.status = status; }
    // }
}
