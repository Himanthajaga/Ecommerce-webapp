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

@WebServlet(name = "ViewOrdersServlet", urlPatterns = "/viewOrders")
public class ViewOrdersServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(OrderHistoryServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderDTO> orders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Ijse@123")) {
            String sql = "SELECT order_id, user_id, order_date, total_amount, status, payment_method, discount FROM orders";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        OrderDTO order = new OrderDTO();
                        order.setOrder_id(resultSet.getInt("order_id"));
                        order.setUser_id(resultSet.getInt("user_id"));
                        order.setOrder_date(resultSet.getDate("order_date").toString());
                        order.setTotalAmount(resultSet.getDouble("total_amount"));
                        order.setStatus(resultSet.getString("status"));
                        order.setPaymentMethod(resultSet.getString("payment_method"));
                        order.setDiscount(resultSet.getDouble("discount"));

                        // Retrieve order items
                        String itemSql = "SELECT product_id, quantity, price FROM order_items WHERE order_id = ?";
                        try (PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
                            itemStatement.setInt(1, order.getOrder_id());
                            try (ResultSet itemResultSet = itemStatement.executeQuery()) {
                                List<OrderDetailDTO> orderItems = new ArrayList<>();
                                while (itemResultSet.next()) {
                                    OrderDetailDTO item = new OrderDetailDTO();
                                    item.setProductId(itemResultSet.getInt("product_id"));
                                    item.setQuantity(itemResultSet.getInt("quantity"));
                                    item.setPrice(itemResultSet.getDouble("price"));
                                    orderItems.add(item);
                                }
                                order.setOrderItems(orderItems);
                            }
                        }

                        orders.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ServletException("Database error", e);
        }

        LOGGER.log(Level.INFO, "Orders retrieved: {0}", orders.size());
        req.setAttribute("Allorders", orders);
        req.getRequestDispatcher("admin-orders.jsp").forward(req, resp);
    }
}