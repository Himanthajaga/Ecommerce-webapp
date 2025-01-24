package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.ecomerce.dto.CartDTO;
import lk.ijse.ecomerce.dto.ProductsDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddToCartServlet", value = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String image_url = request.getParameter("image_url");
        String name = request.getParameter("product_name");
        System.out.println("Image URL: " + image_url);

        if (userId != null) {
            // Add item to session cart
            List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }
            CartDTO item = new CartDTO();
            item.setProduct_id(productId);
            item.setQuantity(quantity);
            item.setPrice(price);
            item.setImage_url(image_url);
            item.setProduct_name(name);
            cart.add(item);
            session.setAttribute("cart", cart);

            // Save item to database cart table
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Ijse@123")) {
                String sql = "INSERT INTO cart (user_id, product_id, quantity, price, status,image_url,product_name) VALUES (?, ?, ?, ?, 'paid', ?,?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    statement.setInt(2, productId);
                    statement.setInt(3, quantity);
                    statement.setDouble(4, price);
                    statement.setString(5, image_url);
                    statement.setString(6, name);
                    System.out.println("Executing SQL: " + statement);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new ServletException("Database error", e);
            }

            response.sendRedirect("shopping-cart.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        HttpSession session = request.getSession();
        List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        // Assuming you have a method to get product details by ID
        ProductsDTO product = getProductById(productId);
        CartDTO cartItem = new CartDTO();
        cartItem.setProduct_id(product.getProduct_id());
        cartItem.setQuantity(1); // Default quantity
        cartItem.setPrice(product.getPrice());
        cartItem.setImage_url(product.getImage_url());
        cartItem.setProduct_name(product.getName());
        cart.add(cartItem);
        session.setAttribute("cart", cart);
        response.sendRedirect("shopping-cart.jsp");
    }

    private ProductsDTO getProductById(int productId) {
        ProductsDTO product = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Ijse@123");

            // Prepare the SQL query
            String sql = "SELECT * FROM products WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                product = new ProductsDTO();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImage_url(resultSet.getString("image_url"));
                product.setName(resultSet.getString("name"));
                // Set other product fields as needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return product;
    }
}
