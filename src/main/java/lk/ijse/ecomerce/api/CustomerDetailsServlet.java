package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.dto.UserDTO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CustomerDetailsServlet", value = "/customer-details")
public class CustomerDetailsServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(CustomerDetailsServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdParam = request.getParameter("customerId");
        UserDTO customer = null;

        try {
            int customerId = Integer.parseInt(customerIdParam);

            try (Connection connection = dataSource.getConnection()) {
                String sql = "SELECT * FROM users WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, customerId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            customer = new UserDTO();
                            customer.setUserId(resultSet.getInt("user_id"));
                            customer.setUserName(resultSet.getString("username"));
                            customer.setPassword(resultSet.getString("password"));
                            customer.setEmail(resultSet.getString("email"));
                            customer.setRole(resultSet.getString("role"));
                            customer.setActive(resultSet.getBoolean("active"));
                        }
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Database error", e);
                throw new ServletException("Database error", e);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid customer ID format", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customer ID format");
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(customer != null ? customer.toJson() : "{}");
    }
}
