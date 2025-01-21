package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.CategoryBO;
import lk.ijse.ecomerce.dto.CategoryDTO;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "AddCategoryServlet", value = "/add-category")
public class AddCategoryServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private CategoryBO categoryBO;

    @Override
    public void init() throws ServletException {
        categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Category);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
       String description = req.getParameter("description");

        if (name == null || name.trim().isEmpty()) {
            resp.sendRedirect("add_category.jsp?error=Category name is required");
            return;
        }

        CategoryDTO category = new CategoryDTO(name, description);

        try {
            categoryBO.addCategory(category);
            // Redirect to view-category servlet
            resp.sendRedirect("view-category");
        } catch (Exception e) {
            throw new ServletException("Error adding category", e);
        }
    }
}
