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
import java.util.List;

@WebServlet(name = "EditCategoryServlet", value = "/edit-category")
public class EditCategoryServlet extends HttpServlet {
    private CategoryBO categoryBO;

    @Override
    public void init() throws ServletException {
        categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Category);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<CategoryDTO> categoryList = categoryBO.getAllCategories();
            request.setAttribute("categoryList", categoryList);

            String categoryId = request.getParameter("id");
            if (categoryId != null) {
                CategoryDTO category = categoryBO.getCategoryById(Integer.parseInt(categoryId));
                request.setAttribute("category", category);
            }

            request.getRequestDispatcher("edit-category.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving categories", e);
        }
    }
}