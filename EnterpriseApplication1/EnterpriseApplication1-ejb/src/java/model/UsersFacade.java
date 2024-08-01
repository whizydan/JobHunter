package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import model.Users;

@Stateless
public class UsersFacade extends Constants{
    
    @Resource(name = "jdbc/_default")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void create(Users user) {
        String sql = "INSERT INTO users (fname, lname, mname, password, email, role, enabled, age, gender, warningcount, warningmessage, commentId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFname());
            stmt.setString(2, user.getLname());
            stmt.setString(3, user.getMname());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getEmail());
            stmt.setInt(6, user.getRole());
            stmt.setBoolean(7, user.isEnabled());
            stmt.setInt(8, user.getAge());
            stmt.setString(9, user.getGender());
            stmt.setInt(10, user.getWarningcount());
            stmt.setString(11, user.getWarningmessage());
            stmt.setInt(12, user.getCommnetId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Users findUserByEmail(String name) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateWarningCount(String id, int commentId){
        Users user = find(id);
        String sql = "Update users set warningcount = ?, warningmessage = ? , commentId = ? where id = " + id;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getWarningcount() + 1);
            stmt.setString(2, "After 3 complaints your account will be terminated");
            stmt.setInt(3, commentId);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Users find(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Users> findAll() {
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usersList.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
    
    public List<Users> findAllStaff() {
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users where role = 0";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usersList.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
    
    public List<Users> findAllCorporate() {
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users where role = 1";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usersList.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
    
    public List<Users> findAllSeekers() {
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users where role = 2";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usersList.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void update(Users user) {
    String sql = "UPDATE users SET fname = ?, lname = ?, email = ?, enabled = ?, age = ?, gender = ? WHERE id = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, user.getFname());
        stmt.setString(2, user.getLname());
        stmt.setString(3, user.getEmail());
        stmt.setBoolean(4, user.isEnabled());
        stmt.setInt(5, user.getAge());
        stmt.setString(6, user.getGender());
        stmt.setLong(7, user.getId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Users mapRowToUser(ResultSet rs) throws SQLException {
        Users user = new Users();
        user.setId(rs.getLong("id"));
        user.setFname(rs.getString("fname"));
        user.setLname(rs.getString("lname"));
        user.setMname(rs.getString("mname"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getInt("role"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setAge(rs.getInt("age"));
        user.setGender(rs.getString("gender"));
        user.setWarningcount(rs.getInt("warningcount"));
        user.setWarningmessage(rs.getString("warningmessage"));
        user.setCommentId(rs.getInt("commentId"));
        return user;
    }
}
