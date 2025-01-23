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

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(AdminServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loggedInAdminId = (String) request.getSession().getAttribute("loggedInAdminId");
        if (loggedInAdminId == null) {
            response.sendRedirect("login.jsp?error=Please log in first");
            return;
        }

        UserDTO admin = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT user_id, username, email, role, active FROM users WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(loggedInAdminId));
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        admin = new UserDTO();
                        admin.setUserId(resultSet.getInt("user_id"));
                        admin.setUserName(resultSet.getString("username"));
                        admin.setEmail(resultSet.getString("email"));
                        admin.setRole(resultSet.getString("role"));
                        admin.setActive(resultSet.getBoolean("active"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }

        request.setAttribute("admin", admin);
        request.getRequestDispatcher("update-admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        int userId = Integer.parseInt(request.getParameter("userId"));

        UserDTO admin = new UserDTO(userId, userName, email, role);
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE users SET username = ?, email = ?, role = ? WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, admin.getUserName());
                statement.setString(2, admin.getEmail());
                statement.setString(3, admin.getRole());
                statement.setInt(4, admin.getUserId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ServletException("Database error", e);
        }

        response.sendRedirect("admin");
    }
}
