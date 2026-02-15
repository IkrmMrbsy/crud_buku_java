
CREATE DATABASE IF NOT EXISTS perpustakaan;
USE perpustakaan;

CREATE TABLE IF NOT EXISTS buku (
    kode_buku VARCHAR(10) PRIMARY KEY,
    judul VARCHAR(100) NOT NULL,
    pengarang VARCHAR(100) NOT NULL,
    tahun INT NOT NULL
);

INSERT INTO buku (kode_buku, judul, pengarang, tahun) VALUES
('B001', 'Belajar Java', 'Ikram', 2026),
('B002', 'Pemrograman GUI', 'Ikram', 2025);
