package dao;

import database.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Buku;

public class BukuDao {

    public boolean insert(Buku buku) {
        String sql = "INSERT INTO buku VALUES (?,?,?,?)";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, buku.getKodeBuku());
            ps.setString(2, buku.getJudul());
            ps.setString(3, buku.getPengarang());
            ps.setInt(4, buku.getTahun());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Insert Gagal: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Buku buku) {
        String sql = "UPDATE buku SET judul=?, pengarang=?, tahun=? WHERE kode_buku=?";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, buku.getJudul());
            ps.setString(2, buku.getPengarang());
            ps.setInt(3, buku.getTahun());
            ps.setString(4, buku.getKodeBuku());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Update Gagal: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String kode) {
        String sql = "DELETE FROM buku WHERE kode_buku=?";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kode);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Delete Gagal: " + e.getMessage());
            return false;
        }
    }

    public List<Buku> getAll() {
        List<Buku> list = new ArrayList<>();
        String sql = "SELECT * FROM buku";

        try (Connection conn = Koneksi.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Buku buku = new Buku(
                        rs.getString("kode_buku"),
                        rs.getString("judul"),
                        rs.getString("pengarang"),
                        rs.getInt("tahun")
                );
                list.add(buku);
            }

        } catch (Exception e) {
            System.out.println("GetAll Gagal: " + e.getMessage());
        }

        return list;
    }

    public List<Buku> search(String keyword) {
        List<Buku> list = new ArrayList<>();
        String sql = "SELECT * FROM buku WHERE kode_buku LIKE ? OR judul LIKE ?";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Buku buku = new Buku(
                        rs.getString("kode_buku"),
                        rs.getString("judul"),
                        rs.getString("pengarang"),
                        rs.getInt("tahun")
                );
                list.add(buku);
            }

        } catch (Exception e) {
            System.out.println("Search Gagal: " + e.getMessage());
        }

        return list;
    }
}
