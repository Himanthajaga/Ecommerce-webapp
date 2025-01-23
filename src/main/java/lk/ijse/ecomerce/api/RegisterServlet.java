package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, email);
            stmt.setString(4, role);
            stmt.executeUpdate();
            resp.sendRedirect("login.jsp?message=Account created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("register.jsp?error=Failed to create account");
        }
    }
}