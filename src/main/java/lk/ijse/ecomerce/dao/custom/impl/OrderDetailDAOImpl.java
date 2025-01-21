package lk.ijse.ecomerce.dao.custom.impl;

import lk.ijse.ecomerce.dao.custom.OrderDetailDAO;
import lk.ijse.ecomerce.entity.OrderDetail;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public OrderDetailDAOImpl(DataSource dataSource) {
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
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
    public OrderDetail search(String id) throws SQLException, ClassNotFoundException {
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
