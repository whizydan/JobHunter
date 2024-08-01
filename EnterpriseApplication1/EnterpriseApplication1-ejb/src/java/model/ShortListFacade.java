/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class ShortListFacade {
    @Resource(name = "jdbc/_default")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public void create(Shortlist shortlist) {
        String sql = "INSERT INTO shortlist (applicationId, offered, offerAccepted, rejectionReason, vacancyId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, shortlist.getApplicationId());
            stmt.setBoolean(2, shortlist.isOffered());
            stmt.setBoolean(3, shortlist.isOfferAccepted());
            stmt.setString(4, shortlist.getRejectionReason());
            stmt.setLong(5, shortlist.getVacancyId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Shortlist find(Long id) {
        String sql = "SELECT * FROM shortlist WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToShortlist(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Shortlist findByApplication(String id) {
        String sql = "SELECT * FROM shortlist WHERE applicationId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToShortlist(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Shortlist> findAll() {
        List<Shortlist> shortlistList = new ArrayList<>();
        String sql = "SELECT * FROM shortlist";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                shortlistList.add(mapRowToShortlist(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shortlistList;
    }
    
    public List<Shortlist> findAllById(String id) {
        List<Shortlist> shortlistList = new ArrayList<>();
        String sql = "SELECT * FROM shortlist where vacancyId = " + id;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                shortlistList.add(mapRowToShortlist(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shortlistList;
    }
    
    public void update(Shortlist shortlist) {
        String sql = "UPDATE shortlist SET applicationId = ?, offered = ?, offerAccepted = ?, rejectionReason = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, shortlist.getApplicationId());
            stmt.setBoolean(2, shortlist.isOffered());
            stmt.setBoolean(3, shortlist.isOfferAccepted());
            stmt.setString(4, shortlist.getRejectionReason());
            stmt.setLong(5, shortlist.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void offerJob(boolean offered, int id){
        String sql = "UPDATE shortlist SET offered = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(2, id);
            stmt.setBoolean(1, offered);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM shortlist WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Shortlist mapRowToShortlist(ResultSet rs) throws SQLException {
        Shortlist shortlist = new Shortlist();
        shortlist.setId(rs.getLong("id"));
        shortlist.setApplicationId(rs.getLong("applicationId"));
        shortlist.setOffered(rs.getBoolean("offered"));
        shortlist.setOfferAccepted(rs.getBoolean("offerAccepted"));
        shortlist.setRejectionReason(rs.getString("rejectionReason"));
        shortlist.setVacancyId(rs.getInt("vacancyId"));
        return shortlist;
    }
}
