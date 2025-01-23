package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.dto.OrderDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "OrderServlet", value = "/orders")
public class OrderServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(OrderServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderDTO> orders = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "Ijse@123")) {
            String sql = "SELECT order_id, user_id, order_date, total_amount, status FROM orders";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        OrderDTO order = new OrderDTO();
                        order.setOrder_id(resultSet.getInt("order_id"));
                        order.setUser_id(resultSet.getInt("user_id"));
                        order.setOrder_date(String.valueOf(resultSet.getDate("order_date")));
                        order.setTotalAmount(resultSet.getDouble("total_amount"));
                        order.setStatus(resultSet.getString("status"));
                        orders.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ServletException("Database error", e);
        }

        LOGGER.log(Level.INFO, "Orders retrieved: {0}", orders.size());
        request.setAttribute("Allorders", orders);
        request.getRequestDispatcher("admin-orders.jsp").forward(request, response);
    }
}