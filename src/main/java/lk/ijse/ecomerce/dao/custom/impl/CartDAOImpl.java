package lk.ijse.ecomerce.dao.custom.impl;

import lk.ijse.ecomerce.dao.custom.CartDAO;
import lk.ijse.ecomerce.entity.Cart;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    private DataSource dataSource = null;

    public CartDAOImpl() {
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<Cart> getAll() throws SQLException, ClassNotFoundException {
        List<Cart> cartList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cart");
            while (rs.next()) {
                Cart cart = new Cart(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                cartList.add(cart);
            }
        }
        return (ArrayList<Cart>) cartList;
    }

    @Override
    public boolean add(Cart cart) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO cart (user_id, product_id, quantity) VALUES (?,?,?)");
            stmt.setInt(1, cart.getUser_id());
            stmt.setInt(2, cart.getProduct_id());
            stmt.setInt(3, cart.getQuantity());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Cart cart) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE cart SET user_id=?, product_id=?, quantity=? WHERE cart_id=?");
            stmt.setInt(1, cart.getUser_id());
            stmt.setInt(2, cart.getProduct_id());
            stmt.setInt(3, cart.getQuantity());
            stmt.setInt(4, cart.getCart_id());
            return stmt.executeUpdate() > 0;
        }
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
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM carts WHERE cart_id=?");
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Cart search(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cart WHERE cart_id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cart(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("status")
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