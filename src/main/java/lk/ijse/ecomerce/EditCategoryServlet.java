package lk.ijse.ecomerce;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "EditCategoryServlet", urlPatterns = "/editCategory")
public class EditCategoryServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categories WHERE id = ?");
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CategoryDTO category = new CategoryDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                req.setAttribute("category", category);
                RequestDispatcher dispatcher = req.getRequestDispatcher("edit_category.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("viewCategories?error=Category not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("viewCategories?error=Failed to load category");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE categories SET name = ?, description = ? WHERE id = ?");
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setInt(3, categoryId);
            stmt.executeUpdate();
            resp.sendRedirect("viewCategories?success=Category updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("viewCategories?error=Failed to update category");
        }
    }
}