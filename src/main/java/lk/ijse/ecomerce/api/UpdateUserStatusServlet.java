package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserStatusServlet", value = "/updateUserStatus")
public class UpdateUserStatusServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        boolean isActive = Boolean.parseBoolean(request.getParameter("is_active"));

        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE users SET active = ? WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, isActive);
                statement.setInt(2, userId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }

        response.sendRedirect("viewUsers");
    }
}