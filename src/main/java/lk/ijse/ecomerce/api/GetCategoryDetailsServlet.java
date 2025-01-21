package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.CategoryBO;
import lk.ijse.ecomerce.dto.CategoryDTO;

import java.io.IOException;

@WebServlet(name = "GetCategoryDetailsServlet", value = "/get-category-details")
public class GetCategoryDetailsServlet extends HttpServlet {
    private CategoryBO categoryBO;

    @Override
    public void init() throws ServletException {
        categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Category);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("category_id");
        try {
            CategoryDTO category = categoryBO.getCategoryById(Integer.parseInt(categoryId));
            response.setContentType("application/json");
            response.getWriter().write("{\"name\":\"" + category.getName() + "\", \"description\":\"" + category.getDescription() + "\"}");
        } catch (Exception e) {
            throw new ServletException("Error retrieving category details", e);
        }
    }
}