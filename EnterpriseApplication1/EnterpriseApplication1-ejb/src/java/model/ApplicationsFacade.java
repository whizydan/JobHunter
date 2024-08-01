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
public class ApplicationsFacade {
    @Resource(name = "jdbc/_default")
    private DataSource dataSource;
    
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    public void create(Applications application) {
        String sql = "INSERT INTO applications (vacancyId, userId, resume, portfolio, email, date, fullname, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, application.getVacancyId());
            stmt.setLong(2, application.getUserId());
            stmt.setString(3, application.getResume());
            stmt.setString(4, application.getPortfolio());
            stmt.setString(5, application.getEmail());
            stmt.setDate(6, new java.sql.Date(application.getDate().getTime()));
            stmt.setString(7, application.getFullname());
            stmt.setString(8, application.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Applications find(Long id) {
        String sql = "SELECT * FROM applications WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToApplication(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Applications> findAll() {
        List<Applications> applicationsList = new ArrayList<>();
        String sql = "SELECT * FROM applications";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                applicationsList.add(mapRowToApplication(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicationsList;
    }
    
    public List<Applications> findAllById(String id) {
        List<Applications> applicationsList = new ArrayList<>();
        String sql = "SELECT * FROM applications where vacancyId = " + id;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                applicationsList.add(mapRowToApplication(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicationsList;
    }
    
    public List<UserApps> findMyApps(String id) {
        List<UserApps> applicationsList = new ArrayList<>();
        String sql = "SELECT \n" +
"applications.id,\n" +
"applications.vacancyId,\n" +
"applications.userId,\n" +
"applications.resume,\n" +
"applications.portfolio,\n" +
"applications.email,\n" +
"applications.date,\n" +
"applications.fullname,\n" +
"applications.phone,\n" +
"vacancies.id as vacId,\n" +
"vacancies.corporateUserId,\n" +
"vacancies.date,\n" +
"vacancies.salary,\n" +
"vacancies.shortDesc,\n" +
"vacancies.longDesc,\n" +
"vacancies.type,\n" +
"vacancies.vacancies,\n" +
"vacancies.email,\n" +
"vacancies.title,\n" +
"vacancies.duration,\n" +
"vacancies.deadline,\n" +
"vacancies.location,\n" +
"vacancies.logo,\n" +
"vacancies.category,\n" +
"vacancies.companyInfo\n" +
"FROM applications inner join vacancies on vacancies.id = applications.vacancyId where applications.userId = " + id;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                UserApps app = new UserApps();
                app.setApplicationDate(rs.getString("date"));
                app.setDeadline(rs.getString("deadline"));
                app.setDuration(rs.getString("duration"));
                app.setId(rs.getInt("id"));
                app.setLogo(rs.getString("logo"));
                app.setSalary(rs.getString("salary"));
                app.setTitle(rs.getString("title"));
                
                applicationsList.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicationsList;
    }
    public void update(Applications application) {
        String sql = "UPDATE applications SET vacancyId = ?, userId = ?, resume = ?, portfolio = ?, email = ?, date = ?, fullname = ?, phone = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, application.getVacancyId());
            stmt.setLong(2, application.getUserId());
            stmt.setString(3, application.getResume());
            stmt.setString(4, application.getPortfolio());
            stmt.setString(5, application.getEmail());
            stmt.setDate(6, new java.sql.Date(application.getDate().getTime()));
            stmt.setString(7, application.getFullname());
            stmt.setString(8, application.getPhone());
            stmt.setLong(9, application.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM applications WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Applications mapRowToApplication(ResultSet rs) throws SQLException {
        Applications application = new Applications();
        application.setId(rs.getLong("id"));
        application.setVacancyId(rs.getLong("vacancyId"));
        application.setUserId(rs.getLong("userId"));
        String resume = rs.getString("resume");
        if(resume.length() > 50){
            application.setResume(rs.getString("resume").substring(0, 50));
        }else{
                application.setResume(rs.getString("resume"));
        }
        application.setPortfolio(rs.getString("portfolio"));
        application.setEmail(rs.getString("email"));
        application.setDate(rs.getDate("date"));
        application.setFullname(rs.getString("fullname"));
        application.setPhone(rs.getString("phone"));
        return application;
    }
}
