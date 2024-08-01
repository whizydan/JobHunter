/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author kerberos
 */
@Stateless
public class CommentsFacade {
    @Resource(name = "jdbc/_default")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public List<Comments> findAll() {
        List<Comments> commentsList = new ArrayList<>();
        String sql = "SELECT * FROM comments";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Comments comment = new Comments();
                comment.setComment(rs.getString("comments"));
                comment.setDate(rs.getDate("date"));
                comment.setId(rs.getLong("id"));
                comment.setUserId(rs.getLong("userId"));
                
                commentsList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentsList;
    }
    public List<Comments> find(String id) {
        List<Comments> commentsList = new ArrayList<>();
        String sql = "SELECT * FROM comments where id = " + id;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Comments comment = new Comments();
                comment.setComment(rs.getString("comment"));
                comment.setDate(rs.getDate("date"));
                comment.setId(rs.getLong("id"));
                comment.setUserId(rs.getLong("userId"));
                comment.setVacancyId(rs.getLong("vacancyId"));
                
                commentsList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentsList;
    }
    public List<Comments> findById(String id) {
        List<Comments> commentsList = new ArrayList<>();
        String sql = "SELECT * FROM comments where vacancyId = " + id;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Comments comment = new Comments();
                comment.setComment(rs.getString("comment"));
                comment.setDate(rs.getDate("date"));
                comment.setId(rs.getLong("id"));
                comment.setUserId(rs.getLong("userId"));
                comment.setVacancyId(rs.getLong("vacancyId"));
                
                commentsList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentsList;
    }
    public void create(Comments comment) {
        String sql = "INSERT INTO comments (comment, date, userId, vacancyId) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, comment.getComment());
            stmt.setDate(2, new Date(System.currentTimeMillis())); // Set the current date
            stmt.setLong(3, comment.getUserId());
            stmt.setLong(4, comment.getVacancyId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
