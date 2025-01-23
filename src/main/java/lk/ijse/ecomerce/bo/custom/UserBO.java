package lk.ijse.ecomerce.bo.custom;

import lk.ijse.ecomerce.bo.SuperBO;
import lk.ijse.ecomerce.dto.UserDTO;
import lk.ijse.ecomerce.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    List<User> getAllUsers() throws SQLException, ClassNotFoundException;
    boolean addUser(User user) throws SQLException, ClassNotFoundException;
    boolean updateUser(User user) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    User searchUser(String id) throws SQLException, ClassNotFoundException;

    void updateUserProfile(UserDTO user);
}
