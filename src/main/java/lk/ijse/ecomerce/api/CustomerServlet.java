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

@WebServlet(name = "CustomerServlet", value = "/customer")
public class CustomerServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(CustomerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loggedInUserId = (String) request.getSession().getAttribute("loggedInUserId");
        if (loggedInUserId == null) {
            response.sendRedirect("login.jsp?error=Please log in first");
            return;
        }

        UserDTO customer = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT user_id, username, email, role, active FROM users WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(loggedInUserId));
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        customer = new UserDTO();
                        customer.setUserId(resultSet.getInt("user_id"));
                        customer.setUserName(resultSet.getString("username"));
                        customer.setEmail(resultSet.getString("email"));
                        customer.setRole(resultSet.getString("role"));
                        customer.setActive(resultSet.getBoolean("active"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }

        request.setAttribute("customer", customer);
        request.getRequestDispatcher("update-customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        boolean active = Boolean.parseBoolean(String.valueOf(request.getParameter("active")));
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDTO user = new UserDTO(userName, email, role, active, userId);
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE users SET username = ?,  email = ?, role = ? WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUserName());

                statement.setString(2, user.getEmail());
                statement.setString(3, user.getRole());
//                statement.setBoolean(4, user.isActive());
                statement.setInt(4, user.getUserId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ServletException("Database error", e);
        }

        response.sendRedirect("customer");
    }
}