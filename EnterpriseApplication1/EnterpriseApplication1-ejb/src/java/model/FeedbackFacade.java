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

/**
 *
 * @author kerberos
 */
@Stateless
public class FeedbackFacade {
    @Resource(name = "jdbc/_default")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public List<UsersFeedback> findAll() {
        List<UsersFeedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM feedback inner join users on feedback.jobSeekerId = users.id";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                UsersFeedback feedback = new UsersFeedback();
                
                feedback.setDate(rs.getString("date"));
                feedback.setFeedback(rs.getString("feedback"));
                feedback.setId(rs.getInt("id"));
                feedback.setIssueType(rs.getString("issueType"));
                feedback.setFname(rs.getString("fname"));
                feedback.setLname(rs.getString("lname"));
                feedback.setWarningCount(rs.getString("warningCount"));
                feedback.setAge(rs.getInt("age"));
                
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }
    
    public UsersFeedback find(String id) {
        String sql = "SELECT * FROM feedback WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UsersFeedback feedback = new UsersFeedback();
                    feedback.setDate(rs.getString("date"));
                    feedback.setFeedback(rs.getString("feedback"));
                    feedback.setId(rs.getInt("id"));
                    feedback.setIssueType(rs.getString("issueType"));
                    feedback.setFname(rs.getString("fname"));
                    feedback.setLname(rs.getString("lname"));
                    feedback.setWarningCount(rs.getString("warningCount"));
                    feedback.setAge(rs.getInt("age"));
                
                    return feedback;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void create(Feedback feedback) {
        String sql = "INSERT INTO feedback (date, feedback, issueType, jobSeekerId, resolved, corporateUserId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(feedback.getDate()));
            stmt.setString(2, feedback.getFeedback());
            stmt.setString(3, feedback.getIssueType());
            stmt.setLong(4, feedback.getJobSeekerId());
            stmt.setBoolean(5, feedback.isResolved());
            stmt.setLong(6, feedback.getCorporateUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
