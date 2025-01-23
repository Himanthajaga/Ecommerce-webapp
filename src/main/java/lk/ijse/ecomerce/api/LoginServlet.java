package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.ecomerce.dto.CartDTO;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT user_id, password, role, active FROM users WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String storedHash = resultSet.getString("password");
                        String role = resultSet.getString("role");
                        boolean active = resultSet.getBoolean("active");
                        if (!active) {
                            response.sendRedirect("login.jsp?error=Your account is suspended");
                            return;
                        }
                        if (BCrypt.checkpw(password, storedHash)) {
                            int userId = resultSet.getInt("user_id");
                            HttpSession session = request.getSession();
                            session.setAttribute("user_id", userId);
                            session.setAttribute("loggedInUserId", String.valueOf(userId));
                            session.setAttribute("loggedInAdminId", String.valueOf(userId));
                            session.setAttribute("role", role);

                            if ("admin".equalsIgnoreCase(role)) {
                                response.sendRedirect("admin_dashboard.jsp");
                            } else if ("customer".equalsIgnoreCase(role)) {
                                List<CartDTO> cart = new ArrayList<>();
                                String cartSql = "SELECT product_id, quantity, price FROM cart WHERE user_id = ?";
                                try (PreparedStatement cartStatement = connection.prepareStatement(cartSql)) {
                                    cartStatement.setInt(1, userId);
                                    try (ResultSet cartResultSet = cartStatement.executeQuery()) {
                                        while (cartResultSet.next()) {
                                            CartDTO item = new CartDTO();
                                            item.setProduct_id(cartResultSet.getInt("product_id"));
                                            item.setQuantity(cartResultSet.getInt("quantity"));
                                            item.setPrice(cartResultSet.getDouble("price"));
                                            cart.add(item);
                                        }
                                    }
                                }
                                session.setAttribute("cart", cart);
                                response.sendRedirect("customerdashboard.jsp");
                            } else {
                                response.sendRedirect("login.jsp?error=Invalid role");
                            }
                        } else {
                            response.sendRedirect("login.jsp?error=Invalid username or password");
                        }
                    } else {
                        response.sendRedirect("login.jsp?error=Invalid username or password");
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}