package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.dto.OrderDTO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewOrdersServlet", urlPatterns = "/viewOrders")
public class ViewOrdersServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderDTO> orderList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");
            while (rs.next()) {
                OrderDTO order = new OrderDTO(rs.getInt("id"), rs.getInt("user_id"), rs.getTimestamp("order_date"), rs.getDouble("total"), rs.getString("status"));
                orderList.add(order);
            }
            req.setAttribute("orderList", orderList);
            req.getRequestDispatcher("view_orders.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("admin_dashboard.jsp?error=Failed to load orders");
        }
    }
}