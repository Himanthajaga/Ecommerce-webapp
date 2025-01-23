package lk.ijse.ecomerce.bo.custom.impl;

import lk.ijse.ecomerce.bo.custom.UserBO;
import lk.ijse.ecomerce.dao.DAOFactory;
import lk.ijse.ecomerce.dao.custom.UserDAO;
import lk.ijse.ecomerce.dto.UserDTO;
import lk.ijse.ecomerce.entity.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Users);

    public UserBOImpl(DataSource dataSource) {

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

    @Override
    public void updateUserProfile(UserDTO user) {
        try {
            User entity = new User(user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getRole(), user.isActive());
            userDAO.update(entity);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}