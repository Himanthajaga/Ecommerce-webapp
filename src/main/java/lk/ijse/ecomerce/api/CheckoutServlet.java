package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.CartBO;
import lk.ijse.ecomerce.bo.custom.CategoryBO;
import lk.ijse.ecomerce.bo.custom.ProductBO;
import lk.ijse.ecomerce.dto.CartDTO;
import lk.ijse.ecomerce.dto.ProductsDTO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private ProductBO productBO;
    private CartBO cartBO;

    @Override
    public void init() throws ServletException {
        productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Product);
        cartBO = (CartBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Cart);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        String orderDate = request.getParameter("orderDate");
        String totalAmount = request.getParameter("totalAmount");
        String status = request.getParameter("status");
        String paymentMethod = request.getParameter("paymentMethod");
        String[] productIds = request.getParameterValues("product_id");

        if (userId != null && productIds != null && orderDate != null && totalAmount != null && status != null && paymentMethod != null) {
            try (Connection connection = dataSource.getConnection()) {
                String insertOrderSql = "INSERT INTO orders (user_id, order_date, total_amount, status, payment_method) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement orderStatement = connection.prepareStatement(insertOrderSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    orderStatement.setInt(1, userId);
                    orderStatement.setString(2, orderDate);
                    orderStatement.setDouble(3, Double.parseDouble(totalAmount));
                    orderStatement.setString(4, status);
                    orderStatement.setString(5, paymentMethod);
                    orderStatement.executeUpdate();

                    try (var generatedKeys = orderStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int orderId = generatedKeys.getInt(1);

                            String insertOrderDetailsSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement orderDetailsStatement = connection.prepareStatement(insertOrderDetailsSql)) {
                                for (String productIdStr : productIds) {
                                    int productId = Integer.parseInt(productIdStr);
                                    int quantity = Integer.parseInt(request.getParameter("quantity_" + productId));
                                    double price = Double.parseDouble(request.getParameter("price_" + productId));

                                    orderDetailsStatement.setInt(1, orderId);
                                    orderDetailsStatement.setInt(2, productId);
                                    orderDetailsStatement.setInt(3, quantity);
                                    orderDetailsStatement.setDouble(4, price);
                                    orderDetailsStatement.executeUpdate();
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                throw new ServletException("Database error", e);
            }

            response.sendRedirect("order-confirmation.jsp");
        } else {
            response.sendRedirect("checkout.jsp");
        }
    }


    private ProductsDTO getProductById(int productId) {
        ProductsDTO product = null;
        String sql = "SELECT product_id, name, price FROM products WHERE product_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new ProductsDTO();
                    product.setProduct_id(resultSet.getInt("product_id"));
                    product.setName(resultSet.getString("name"));
                    product.setPrice(resultSet.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
