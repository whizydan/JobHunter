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

@Stateless
public class VacanciesFacade extends Constants {

    @Resource(name = "jdbc/_default")
    private DataSource dataSource;

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void create(Vacancies vacancy) {
        String sql = "INSERT INTO vacancies (corporateUserId, date, salary, shortDesc, longDesc, type, vacancies, email, title, duration, deadline, location, logo, category, companyInfo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, vacancy.getCorporateUserId());
            stmt.setDate(2, new Date(vacancy.getDate().getTime()));
            stmt.setString(3, vacancy.getSalary());
            stmt.setString(4, vacancy.getShortDesc());
            stmt.setString(5, vacancy.getLongDesc());
            stmt.setString(6, vacancy.getType());
            stmt.setInt(7, vacancy.getVacancies());
            stmt.setString(8, vacancy.getEmail());
            stmt.setString(9, vacancy.getTitle());
            stmt.setString(10, vacancy.getDuration());
            stmt.setDate(11, new Date(vacancy.getDeadline().getTime()));
            stmt.setString(12, vacancy.getLocation());
            stmt.setString(13, vacancy.getLogo());
            stmt.setString(14, vacancy.getCategory());
            stmt.setString(15, vacancy.getCompanyInfo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vacancies find(Long id) {
        String sql = "SELECT * FROM vacancies WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToVacancy(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Vacancies> findAll() {
        List<Vacancies> vacanciesList = new ArrayList<>();
        String sql = "SELECT * FROM vacancies";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                vacanciesList.add(mapRowToVacancy(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacanciesList;
    }
    
    public List<Vacancies> findAllMine(String userid) {
        List<Vacancies> vacanciesList = new ArrayList<>();
        String sql = "SELECT * FROM vacancies where corporateUserId = " + userid;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                vacanciesList.add(mapRowToVacancy(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacanciesList;
    }

    public void update(Vacancies vacancy) {
        String sql = "UPDATE vacancies SET title = ?, salary = ?, shortDesc = ?, type = ?, category = ?, email = ?, vacancies = ?, duration = ?, deadline = ?, location = ?, logo = ?, companyInfo = ?, longDesc = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vacancy.getTitle());
            stmt.setString(2, vacancy.getSalary());
            stmt.setString(3, vacancy.getShortDesc());
            stmt.setString(4, vacancy.getType());
            stmt.setString(5, vacancy.getCategory());
            stmt.setString(6, vacancy.getEmail());
            stmt.setInt(7, vacancy.getVacancies());
            stmt.setString(8, vacancy.getDuration());
            stmt.setDate(9, new Date(vacancy.getDeadline().getTime()));
            stmt.setString(10, vacancy.getLocation());
            stmt.setString(11, vacancy.getLogo());
            stmt.setString(12, vacancy.getCompanyInfo());
            stmt.setString(13, vacancy.getLongDesc());
            stmt.setLong(14, vacancy.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(Long id) {
        String sql = "DELETE FROM vacancies WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Vacancies mapRowToVacancy(ResultSet rs) throws SQLException {
        Vacancies vacancy = new Vacancies();
        vacancy.setId(rs.getLong("id"));
        vacancy.setCorporateUserId(rs.getLong("corporateUserId"));
        vacancy.setDate(rs.getDate("date"));
        vacancy.setSalary(rs.getString("salary"));
        vacancy.setShortDesc(rs.getString("shortDesc"));
        vacancy.setLongDesc(rs.getString("longDesc"));
        vacancy.setType(rs.getString("type"));
        vacancy.setVacancies(rs.getInt("vacancies"));
        vacancy.setEmail(rs.getString("email"));
        vacancy.setTitle(rs.getString("title"));
        vacancy.setDuration(rs.getString("duration"));
        vacancy.setDeadline(rs.getDate("deadline"));
        vacancy.setLocation(rs.getString("location"));
        vacancy.setLogo(rs.getString("logo"));
        vacancy.setCategory(rs.getString("category"));
        vacancy.setCompanyInfo(rs.getString("companyInfo"));
        return vacancy;
    }
}
