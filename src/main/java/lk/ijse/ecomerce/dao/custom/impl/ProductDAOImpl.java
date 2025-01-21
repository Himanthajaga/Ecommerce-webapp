package lk.ijse.ecomerce.dao.custom.impl;

import jakarta.annotation.Resource;
import lk.ijse.ecomerce.dao.custom.ProductDAO;
import lk.ijse.ecomerce.entity.Product;
import lk.ijse.ecomerce.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Resource(name = "java:comp/env/jdbc/pool")
    private final DataSource dataSource;

    public ProductDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public ArrayList<Product> getAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("category_id"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image_url")
                );
                productList.add(product);
            }
        }
        return (ArrayList<Product>) productList;
    }
    public boolean resetAutoIncrement() throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.execute("ALTER TABLE products AUTO_INCREMENT = 1");
            return true;
        }
    }

    @Override
    public boolean add(Product product) throws SQLException {
        if (product == null || product.getName() == null || product.getName().trim().isEmpty()) {
            throw new SQLException("Product name cannot be null or empty");
        }

//        if (!categoryExists(product.getCategory_id())) {
//            throw new SQLException("Category ID does not exist");
//        }

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO products (name, category_id, price, description, image_url) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategory_id());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getDescription());
            stmt.setString(5, product.getImage_url());
            return stmt.executeUpdate() > 0;
        }
    }

    private boolean categoryExists(int categoryId) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM categories WHERE category_id = ?");
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        if (product == null || product.getName() == null || product.getName().trim().isEmpty()) {
            throw new SQLException("Product name cannot be null or empty");
        }

//        if (!categoryExists(product.getCategory_id())) {
//            throw new SQLException("Category ID does not exist");
//        }

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE products SET name=?, category_id=?, price=?, description=?, image_url=? WHERE product_id=?"
            );
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategory_id());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getDescription());
            stmt.setString(5, product.getImage_url());
            stmt.setInt(6, product.getProduct_id());
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
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM products WHERE product_id=?");
            stmt.setString(1, id);
            boolean isDeleted = stmt.executeUpdate() > 0;
            if (isDeleted) {
                // Check if there are no more products
                Statement checkStmt = connection.createStatement();
                ResultSet rs = checkStmt.executeQuery("SELECT COUNT(*) FROM products");
                if (rs.next() && rs.getInt(1) == 0) {
                    // Reset auto-increment value
                    resetAutoIncrement();
                }
            }
            return isDeleted;
        }
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM products WHERE product_id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("category_id"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image_url")
                );
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
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM products WHERE name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (T) new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("category_id"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image_url")
                );
            }
            return null;
        }
    }

    @Override
    public String getLastId() throws SQLException, ClassNotFoundException {
        return null;
    }
}