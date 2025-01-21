package lk.ijse.ecomerce.dao.custom.impl;

import lk.ijse.ecomerce.dao.custom.OrderDAO;
import lk.ijse.ecomerce.entity.Order;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    public OrderDAOImpl(DataSource dataSource) {
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
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
