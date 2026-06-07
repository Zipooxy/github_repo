# Barkas Project DPBO

Aplikasi marketplace **barang bekas** berbasis terminal (CLI) yang dibuat menggunakan Java OOP. Proyek ini merupakan tugas mata kuliah **Desain Pemrograman Berbasis Objek (DPBO)**.

![Java CI/CD](https://github.com/<USERNAME>/<REPO>/actions/workflows/ci.yml/badge.svg)

---

## Fitur Aplikasi

- **Registrasi & Login** pengguna dengan validasi
- **Lihat Produk** berdasarkan kategori (Fashion, Electronic, Living)
- **Beli Produk** dengan pengecekan saldo
- **Jual Produk** (tambah produk ke marketplace)
- **Menu User** — daftar member & tambah saldo
- **History Transaksi** pembelian
- **Diskon 10%** untuk pengguna member

---

## Struktur Proyek

```
BarkasProjectDPBO/
├── .github/
│   └── workflows/
│       └── ci.yml          # GitHub Actions CI/CD
├── .settings/              # Konfigurasi IDE (NetBeans)
├── bin/                    # Output JAR hasil build
├── src/
│   ├── main/java/
│   │   ├── Exception/      # Custom exceptions
│   │   ├── Main/           # Entry point aplikasi
│   │   ├── Model/          # Kelas model (User, Product, dll)
│   │   ├── Service/        # Logika bisnis
│   │   └── Utils/          # Utilitas input
│   └── test/java/
│       ├── Model/          # Unit test model
│       └── Service/        # Unit test service
├── .gitattributes
├── .gitignore
├── pom.xml                 # Maven configuration
└── README.md
```

---

## Cara Menjalankan

### Prasyarat
- Java 17+
- Apache Maven 3.6+

### Build & Run

```bash
# Clone repository
git clone https://github.com/<USERNAME>/<REPO>.git
cd BarkasProjectDPBO

# Compile
mvn compile

# Jalankan aplikasi
mvn exec:java -Dexec.mainClass="Main.Main"
```

### Menjalankan Unit Test

```bash
mvn test
```

Laporan test tersedia di `target/surefire-reports/`.

### Build JAR

```bash
mvn package
# JAR tersimpan di folder bin/
java -jar bin/BarkasProjectDPBO-1.0-SNAPSHOT.jar
```

---

## CI/CD (GitHub Actions)

Pipeline otomatis berjalan setiap kali ada **push** atau **pull request** ke branch `main`/`develop`:

| Job | Deskripsi |
|-----|-----------|
| **Build & Test** | Compile kode + jalankan semua unit test |
| **Package JAR** | Buat file JAR (hanya di branch `main`) |

Artifact hasil build (JAR & laporan test) dapat diunduh dari tab **Actions** di GitHub.

---

## Teknologi

- **Java 17**
- **Maven** (build tool)
- **JUnit 5** (unit testing)
- **GitHub Actions** (CI/CD)
