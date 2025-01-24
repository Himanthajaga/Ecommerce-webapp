package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.ecomerce.dto.OrderDTO;
import lk.ijse.ecomerce.dto.OrderDetailDTO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "OrderHistoryServlet", urlPatterns = "/order-history")
public class OrderHistoryServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(OrderHistoryServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        LOGGER.log(Level.INFO, "OrderHistoryServlet called");
        LOGGER.log(Level.INFO, "User ID: {0}", userId);

        if (userId != null) {
            try (Connection connection = dataSource.getConnection()) {
                String orderSql = "SELECT * FROM orders WHERE user_id = ?";
                try (PreparedStatement orderStatement = connection.prepareStatement(orderSql)) {
                    orderStatement.setInt(1, userId);
                    try (ResultSet orderResultSet = orderStatement.executeQuery()) {
                        List<OrderDTO> orders = new ArrayList<>();
                        while (orderResultSet.next()) {
                            int orderId = orderResultSet.getInt("order_id");
                            String orderDate = orderResultSet.getString("order_date");
                            double totalAmount = orderResultSet.getDouble("total_amount");
                            String status = orderResultSet.getString("status");
                            String paymentMethod = orderResultSet.getString("payment_method");
                            double discount = orderResultSet.getDouble("discount");

                            List<OrderDetailDTO> orderItems = new ArrayList<>();
                            String orderItemSql = "SELECT * FROM order_items WHERE order_id = ?";
                            try (PreparedStatement orderItemStatement = connection.prepareStatement(orderItemSql)) {
                                orderItemStatement.setInt(1, orderId);
                                try (ResultSet orderItemResultSet = orderItemStatement.executeQuery()) {
                                    while (orderItemResultSet.next()) {
                                        int productId = orderItemResultSet.getInt("product_id");
                                        int quantity = orderItemResultSet.getInt("quantity");
                                        double price = orderItemResultSet.getDouble("price");
                                        String productName = orderItemResultSet.getString("product_name");
                                        orderItems.add(new OrderDetailDTO(orderId, productId, quantity, price, productName));
                                    }
                                }
                            }

                            orders.add(new OrderDTO(orderId, userId, orderDate, totalAmount, status, paymentMethod, discount, orderItems));
                        }
                        LOGGER.log(Level.INFO, "Orders retrieved: {0}", orders.size());
                        request.setAttribute("Allorders", orders);
                        request.getRequestDispatcher("order-history.jsp").forward(request, response);
                    }
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Database error", e);
                throw new ServletException("Database error", e);
            }
        } else {
            LOGGER.log(Level.WARNING, "User ID is null, redirecting to login page");
            response.sendRedirect("login.jsp");
        }
    }
}