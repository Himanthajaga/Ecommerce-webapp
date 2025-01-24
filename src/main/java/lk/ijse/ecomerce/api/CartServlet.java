package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.CartBO;
import lk.ijse.ecomerce.dto.CartDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    private CartBO cartBO;

    @Override
    public void init() throws ServletException {
        cartBO = (CartBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Cart);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        if (userId != null) {
            List<CartDTO> cart = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Ijse@123")) {
                String sql = "SELECT product_id, quantity, price FROM cart WHERE user_id = ? AND status = 'not paid'";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            CartDTO item = new CartDTO();
                            item.setProduct_id(resultSet.getInt("product_id"));
                            item.setQuantity(resultSet.getInt("quantity"));
                            item.setPrice(resultSet.getDouble("price"));
                            cart.add(item);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new ServletException("Database error", e);
            }

            session.setAttribute("cart", cart);
            request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String action = request.getParameter("action");
            if ("add".equals(action)) {
                addToCart(request, response);
            } else if ("update".equals(action)) {
                updateCart(request, response);
            } else if ("remove".equals(action)) {
                removeFromCart(request, response);
            }
        }

        private void addToCart (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            try {
                cartBO.addToCart(productId, quantity);
                response.sendRedirect("cart");
            } catch (Exception e) {
                throw new ServletException("Error adding to cart", e);
            }
        }

        private void updateCart (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            try {
                cartBO.updateCart(productId, quantity);
                response.sendRedirect("cart");
            } catch (Exception e) {
                throw new ServletException("Error updating cart", e);
            }
        }

        private void removeFromCart (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            int productId = Integer.parseInt(request.getParameter("product_id"));

            try {
                cartBO.removeFromCart(productId);
                response.sendRedirect("cart");
            } catch (Exception e) {
                throw new ServletException("Error removing from cart", e);
            }
        }

            }
