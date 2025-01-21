package lk.ijse.ecomerce.dao.custom.impl;

import lk.ijse.ecomerce.dao.custom.UserDAO;
import lk.ijse.ecomerce.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final DataSource dataSource;

    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getInt("active")
                );
                userList.add(user);
            }
        }
        return (ArrayList<User>) userList;
    }

    @Override
    public boolean add(User user) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (username, password, email, role, active) VALUES (?,?,?,?,?)");
            stmt.setString(1, user.getUser_name());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getActive());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET username=?, password=?, email=?, role=?, active=? WHERE user_id=?");
            stmt.setString(1, user.getUser_name());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getActive());
            stmt.setInt(6, user.getUser_id());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT user_id FROM users WHERE user_id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1");
            if (rs.next()) {
                String id = rs.getString("user_id");
                int newUserId = Integer.parseInt(id.replace("U00-", "")) + 1;
                return String.format("U00-%03d", newUserId);
            } else {
                return "U00-001";
            }
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE user_id=?");
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE user_id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getInt("active")
                );
            }
        }
        return null;
    }

    @Override
    public <T> T searchByEmail(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public <T> ArrayList<T> getEmails(String email) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public <T> T searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public <T> T searchByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        return null;
    }
}