package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.ecomerce.dto.CartDTO;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PurchaseOrderServlet", value = "/purchase-order")
public class PurchaseOrderServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PurchaseOrderServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        String userIdStr = userId != null ? userId.toString() : null;
        String orderDate = request.getParameter("orderDate");
        String totalAmount = request.getParameter("totalAmount");
        String status = request.getParameter("status");
        String paymentMethod = request.getParameter("paymentMethod");
        String[] selectedProductIds = request.getParameterValues("product_id");
        String product_name = request.getParameter("product_name");
        List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");

        LOGGER.log(Level.INFO, "User ID: {0}, Order Date: {1}, Total Amount: {2}, Status: {3}, Payment Method: {4}",
                new Object[]{userIdStr, orderDate, totalAmount, status, paymentMethod});

        if (cart != null && selectedProductIds != null && userIdStr != null && orderDate != null && totalAmount != null && status != null && paymentMethod != null) {
            Connection connection = null;
            PreparedStatement orderStatement = null;
            PreparedStatement orderItemStatement = null;
            PreparedStatement deleteCartStatement = null;

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Ijse@123");
                connection.setAutoCommit(false);

                String orderSql = "INSERT INTO orders (user_id, order_date, total_amount, status, payment_method) VALUES (?, ?, ?, ?, ?)";
                orderStatement = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
                orderStatement.setString(1, userIdStr);
                orderStatement.setString(2, orderDate);
                orderStatement.setString(3, totalAmount);
                orderStatement.setString(4, status);
                orderStatement.setString(5, paymentMethod);
                orderStatement.executeUpdate();

                ResultSet generatedKeys = orderStatement.getGeneratedKeys();
                int orderId = 0;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }

                String orderItemSql = "INSERT INTO order_items (order_id, product_id, quantity, price,product_name) VALUES (?, ?, ?, ?, ?)";
                orderItemStatement = connection.prepareStatement(orderItemSql);
                for (CartDTO item : cart) {
                    for (String selectedProductId : selectedProductIds) {
                        if (item.getProduct_id() == Integer.parseInt(selectedProductId)) {
                            int quantity = Integer.parseInt(request.getParameter("quantity_"+selectedProductId));
                            orderItemStatement.setInt(1, orderId);
                            orderItemStatement.setInt(2, item.getProduct_id());
                            orderItemStatement.setInt(3, quantity);
                            orderItemStatement.setDouble(4, item.getPrice());
                            orderItemStatement.setString(5, item.getProduct_name());
                            orderItemStatement.addBatch();
                        }
                    }
                }
                orderItemStatement.executeBatch();

                String deleteCartSql = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
                deleteCartStatement = connection.prepareStatement(deleteCartSql);
                for (String selectedProductId : selectedProductIds) {
                    deleteCartStatement.setInt(1, userId);
                    deleteCartStatement.setInt(2, Integer.parseInt(selectedProductId));
                    deleteCartStatement.addBatch();
                }
                deleteCartStatement.executeBatch();

                connection.commit();

                cart.removeIf(item -> {
                    for (String selectedProductId : selectedProductIds) {
                        if (item.getProduct_id() == Integer.parseInt(selectedProductId)) {
                            return true;
                        }
                    }
                    return false;
                });

                session.setAttribute("cart", cart);
                response.sendRedirect("order-confirmation.jsp");
            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                LOGGER.log(Level.SEVERE, "Error processing order", e);
                throw new ServletException("Error processing order", e);
            } finally {
                try {
                    if (orderItemStatement != null) orderItemStatement.close();
                    if (orderStatement != null) orderStatement.close();
                    if (deleteCartStatement != null) deleteCartStatement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            LOGGER.log(Level.WARNING, "Invalid parameters or empty cart");
            response.sendRedirect("checkout.jsp");
        }
    }
}