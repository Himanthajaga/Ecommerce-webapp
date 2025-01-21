package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.CategoryBO;

import java.io.IOException;

@WebServlet(name = "DeleteCategoryServlet", value = "/delete-category")
public class DeleteCategoryServlet extends HttpServlet {
    private CategoryBO categoryBO;

    @Override
    public void init() throws ServletException {
        categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Category);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
            throw new ServletException("Category ID is missing");
        }

        try {
            categoryBO.deleteCategory(String.valueOf(Integer.parseInt(id)));
            response.sendRedirect("view-category");
        } catch (Exception e) {
            throw new ServletException("Error deleting category", e);
        }
    }
}