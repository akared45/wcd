package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean checkLogin(String user, String pass) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(String user, String pass) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, user);
            ps.setString(2, pass);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("Lỗi đăng ký: " + e.getMessage());
        }
        return false;
    }
}