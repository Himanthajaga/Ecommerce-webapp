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

@WebServlet(name = "UpdateCategoryServlet", value = "/update-category")
public class UpdateCategoryServlet extends HttpServlet {
    private CategoryBO categoryBO;

    @Override
    public void init() throws ServletException {
        categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Category);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (id == null || id.isEmpty()) {
            throw new ServletException("Category ID is missing");
        }

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_id(Integer.parseInt(id));
        categoryDTO.setName(name);
        categoryDTO.setDescription(description);

        try {
            categoryBO.updateCategory(categoryDTO);
            response.sendRedirect("view-category");
        } catch (Exception e) {
            throw new ServletException("Error updating category", e);
        }
    }
}