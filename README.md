# rental-kendaraan-be

Backend API untuk sistem rental/penyewaan kendaraan (mobil & motor) — menangani manajemen data kendaraan, proses transaksi penyewaan, hingga pencatatan pembayaran. Menyediakan otentikasi berbasis peran (admin & penyewa) sehingga pengelolaan armada dan transaksi dapat dilakukan secara terpusat.

## Tech Stack

- **Bahasa & Framework:** Java 21, Spring Boot 3.5.0
- **Build tool:** Maven
- **Database:** MySQL (Spring Data JPA / Hibernate)
- **Keamanan:** Spring Security + JWT (`io.jsonwebtoken`), BCrypt password encoder
- **Validasi:** Spring Validation
- **Monitoring:** Spring Boot Actuator
- **Testing:** JUnit 5, Testcontainers (MySQL)
- **Lainnya:** Lombok

## Fitur Utama

**Autentikasi & Otorisasi**
- Registrasi dan login pengguna dengan JWT
- Role berbasis pengguna: `ADMIN` dan `PENYEWA`

**Manajemen Pengguna**
- CRUD data pengguna

**Manajemen Kendaraan**
- CRUD data kendaraan dengan struktur turunan **Mobil** dan **Motor** (atribut umum: nama, jenis, nomor polisi, tahun, status ketersediaan, harga, transmisi, bahan bakar, gambar)
- Upload gambar kendaraan
- Atribut khusus Mobil (tipe & kapasitas penumpang) dan Motor (tipe & kapasitas mesin)
- Status kendaraan: Tersedia, Disewa, Tidak Tersedia

**Transaksi Penyewaan**
- Membuat transaksi sewa kendaraan (per pengguna)
- Melihat transaksi berdasarkan ID, pengguna, atau seluruh data
- Pengecekan status transaksi (selesai/berlangsung) secara otomatis
- Status transaksi: Pending, Sedang Sewa, Selesai

**Pembayaran**
- Pencatatan pembayaran per transaksi (jumlah bayar & kembalian)
- Status pembayaran: Pending, Lunas, Gagal

## Instalasi & Menjalankan Proyek

### Prasyarat
- JDK 21
- Maven
- MySQL

### Langkah instalasi

```bash
# 1. Clone repository
git clone <repository-url>
cd rental-kendaraan-be

# 2. Siapkan database MySQL, lalu buat file konfigurasi
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Sesuaikan `application.properties` dengan konfigurasi environment kamu sendiri (koneksi database MySQL & JWT secret) — **jangan pernah meng-commit file ini dengan kredensial asli**.

```bash
# 3. Build project
mvn clean install

# 4. Jalankan aplikasi
mvn spring-boot:run
```

Server berjalan secara default di endpoint `/api/**`, dengan `/api/auth/**` dan `/uploads/**` bersifat publik (tanpa autentikasi); endpoint lainnya memerlukan JWT Bearer token.

## Struktur Folder Singkat

```
src/main/java/com/kelompok3/rental_kendaraan_be/
├── controller/    # AuthController, UserController, KendaraanController,
│              # MobilController, MotorController, TransaksiController,
│              # PembayaranController
├── entity/      # Kendaraan (base), Mobil, Motor, User, Transaksi, Pembayaran
├── repository/    # Data access layer (Spring Data JPA)
├── service/      # Business logic
└── security/     # JWT filter, entry point, konfigurasi Spring Security
```

> **Catatan:** File `application.properties.example` perlu dibuat manual (tanpa kredensial asli) sebagai template untuk developer lain — lihat bagian instalasi di atas.
