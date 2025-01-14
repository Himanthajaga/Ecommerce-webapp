package lk.ijse.ecomerce;

import jakarta.annotation.Resource;
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

@WebServlet(name = "DeleteCategoryServlet", urlPatterns = "/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM categories WHERE id = ?");
            stmt.setInt(1, categoryId);
            stmt.executeUpdate();
            resp.sendRedirect("viewCategories?success=Category deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("viewCategories?error=Failed to delete category");
        }
    }
}