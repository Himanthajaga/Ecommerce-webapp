package lk.ijse.ecomerce.bo.custom.impl;

import lk.ijse.ecomerce.bo.custom.UserBO;
import lk.ijse.ecomerce.dao.custom.UserDAO;
import lk.ijse.ecomerce.dao.custom.impl.UserDAOImpl;
import lk.ijse.ecomerce.entity.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO;

    public UserBOImpl(DataSource dataSource) {
        userDAO = new UserDAOImpl(dataSource);
    }

    @Override
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return userDAO.getAll();
    }

    @Override
    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        return userDAO.add(user);
    }

    @Override
    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        return userDAO.update(user);
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public User searchUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.search(id);
    }
}