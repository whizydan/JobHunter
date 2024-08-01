package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class TablesFacade extends Constants {
    
    @Resource(name = "jdbc/_default")
    private DataSource dataSource;

    public void createTablesIfNotExist() {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            // Create tables if they do not exist
            createUsersTable(stmt);
            createApplicationsTable(stmt);
            createCommentsTable(stmt);
            createFeedbackTable(stmt);
            createShortlistTable(stmt);
            createVacanciesTable(stmt);
            
            System.out.println("Tables created successfully (if not already existing).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUsersTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "fname VARCHAR(255),"
                + "lname VARCHAR(255),"
                + "mname VARCHAR(255),"
                + "password VARCHAR(255),"
                + "email VARCHAR(255),"
                + "role INT,"
                + "enabled BOOLEAN,"
                + "age INT,"
                + "gender VARCHAR(10),"
                + "warningcount INT,"
                + "warningmessage TEXT,"
                + "commentId INT"
                + ")";
        stmt.executeUpdate(sql);
    }

    private void createApplicationsTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS applications ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "vacancyId BIGINT,"
                + "userId BIGINT,"
                + "resume TEXT,"
                + "portfolio TEXT,"
                + "email VARCHAR(255),"
                + "date DATE,"
                + "fullname VARCHAR(255),"
                + "phone VARCHAR(20)"
                + ")";
        stmt.executeUpdate(sql);
    }

    private void createCommentsTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS comments ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "userId BIGINT,"
                + "date DATE,"
                + "comment TEXT"
                + ")";
        stmt.executeUpdate(sql);
    }

    private void createFeedbackTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS feedback ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "corporateUserId BIGINT,"
                + "date DATE,"
                + "feedback TEXT,"
                + "jobSeekerId BIGINT,"
                + "issueType VARCHAR(255),"
                + "resolved BOOLEAN"
                + ")";
        stmt.executeUpdate(sql);
    }

    private void createShortlistTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS shortlist ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "applicationId BIGINT,"
                + "vacancyId BIGINT null,"
                + "offered BOOLEAN,"
                + "offerAccepted BOOLEAN,"
                + "rejectionReason TEXT"
                + ")";
        stmt.executeUpdate(sql);
    }

    private void createVacanciesTable(Statement stmt) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS vacancies ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "corporateUserId BIGINT,"
                + "date DATE,"
                + "salary VARCHAR(50),"
                + "shortDesc TEXT,"
                + "longDesc TEXT,"
                + "type VARCHAR(50),"
                + "vacancies INT,"
                + "email VARCHAR(255),"
                + "title VARCHAR(255),"
                + "duration VARCHAR(50),"
                + "deadline DATE,"
                + "location VARCHAR(255),"
                + "logo VARCHAR(255),"
                + "category VARCHAR(255),"
                + "companyInfo TEXT"
                + ")";
        stmt.executeUpdate(sql);
    }
}
