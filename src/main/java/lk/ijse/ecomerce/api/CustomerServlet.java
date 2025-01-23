package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.dto.UserDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CustomerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDTO> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Ijse@123")) {
            String sql = "SELECT user_id, username,password, email,active FROM users WHERE role = 'customer'";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        UserDTO customer = new UserDTO();
                        customer.setUserId(resultSet.getInt("user_id"));
                        customer.setUserName(resultSet.getString("username"));
                        customer.setPassword(resultSet.getString("password"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setActive(resultSet.getBoolean("active"));
                        customers.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ServletException("Database error", e);
        }

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("update-customer.jsp").forward(request, response);
    }
}
