package lk.ijse.ecomerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String product_id = req.getParameter("product_id");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int qty = Integer.parseInt(req.getParameter("qty"));
        double total = price * qty;

        try {
            DataSource ds = (DataSource) getServletContext().getAttribute("ds");
            Connection conn = ds.getConnection();

            String sql = "INSERT INTO cart (product_id, name, price, qty, total) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product_id);
            stmt.setString(2, name);
            stmt.setDouble(3, price);
            stmt.setInt(4, qty);
            stmt.setDouble(5, total);

            stmt.executeUpdate();
            conn.close();

            resp.sendRedirect("cart.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
