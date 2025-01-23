package lk.ijse.ecomerce.dao.custom.impl;

import lk.ijse.ecomerce.dao.custom.OrderDAO;
import lk.ijse.ecomerce.entity.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private DataSource dataSource;

    public OrderDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `order`");
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getInt("order_id"), resultSet.getDate("date"), resultSet.getDouble("total"), resultSet.getString("status")));
            }
        }
        return (ArrayList<Order>) orders;
    }

    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO `order` VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getOrder_id());
            preparedStatement.setDate(2,  Date.valueOf((order.getOrder_date())));
            preparedStatement.setDouble(3, order.getTotal_amount());
            preparedStatement.setString(4, order.getStatus());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE `order` SET date = ?, total = ?, status = ? WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf((order.getOrder_date())));
            preparedStatement.setDouble(2, order.getTotal_amount());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setInt(4, order.getOrder_id());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT 1 FROM `order` WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM `order` WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM `order` WHERE order_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Order(resultSet.getInt("order_id"), resultSet.getDate("date"), resultSet.getDouble("total"), resultSet.getString("status"));
            }
            return null;
        }
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