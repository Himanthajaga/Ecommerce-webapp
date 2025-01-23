package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.ecomerce.dto.CartDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RemoveFromCartServlet", urlPatterns = "/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        int productId = Integer.parseInt(request.getParameter("product_id"));

        if (userId != null) {
            // Remove item from session cart
            List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
            if (cart != null) {
                cart.removeIf(item -> item.getProduct_id() == productId);
                session.setAttribute("cart", cart);
            }

            // Remove item from database cart table
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Ijse@123")) {
                String sql = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    statement.setInt(2, productId);
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
}
