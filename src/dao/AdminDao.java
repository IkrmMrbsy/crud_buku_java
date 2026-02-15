package dao;

import database.Koneksi;
import java.sql.*;
import model.Admin;

public class AdminDao {

    public boolean register(Admin admin) {
        String sql = "INSERT INTO admin (username, password) VALUES (?, ?)";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin.getUsername());
            String hashed = utils.HashUtil.sha256(admin.getPassword());
            ps.setString(2, hashed);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Register Gagal: " + e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username=? AND password=?";

        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            String hashed = utils.HashUtil.sha256(password);
            ps.setString(2, hashed);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Login Gagal: " + e.getMessage());
            return false;
        }
    }

    public boolean gantiPassword(String username, String oldPass, String newPass) {
        String cek = "SELECT * FROM admin WHERE username=? AND password=?";
        String update = "UPDATE admin SET password=? WHERE username=?";

        try (Connection conn = Koneksi.getConnection()) {

            String hashedOld = utils.HashUtil.sha256(oldPass);
            String hashedNew = utils.HashUtil.sha256(newPass);

            PreparedStatement psCek = conn.prepareStatement(cek);
            psCek.setString(1, username);
            psCek.setString(2, hashedOld);
            ResultSet rs = psCek.executeQuery();

            if (rs.next()) {
                PreparedStatement psUpdate = conn.prepareStatement(update);
                psUpdate.setString(1, hashedNew);
                psUpdate.setString(2, username);
                psUpdate.executeUpdate();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Ganti Password Gagal: " + e.getMessage());
        }

        return false;
    }
}
