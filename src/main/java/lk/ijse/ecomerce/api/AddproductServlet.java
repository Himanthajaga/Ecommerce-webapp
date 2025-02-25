package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.CategoryBO;
import lk.ijse.ecomerce.bo.custom.ProductBO;
import lk.ijse.ecomerce.dao.custom.CategoryDAO;
import lk.ijse.ecomerce.dao.custom.impl.CategoryDAOImpl;
import lk.ijse.ecomerce.dto.CategoryDTO;
import lk.ijse.ecomerce.dto.ProductsDTO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet(name = "AddproductServlet", value = "/add-product")
public class AddproductServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private ProductBO productBO;
//    private CategoryBO categoryBO;

    @Override
    public void init() throws ServletException {
        System.out.println("AddproductServlet initialized");
        productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Product);
//        categoryBO = (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Category);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAOImpl(dataSource);
        List<Integer> categoryIds = null;
        try {
            categoryIds = categoryDAO.getAllCategoryIds();
            System.out.println("Category IDs retrieved: " + categoryIds); // Debugging statement
        } catch (SQLException e) {
            e.printStackTrace(); // Log the error
            request.setAttribute("errorMessage", "Error retrieving category IDs.");
        }
        if (categoryIds != null) {
            request.setAttribute("categoryIds", categoryIds);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("add_product.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int categoryId = Integer.parseInt(req.getParameter("category_id"));
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String imageUrl = req.getParameter("image_url");

        if (name == null || name.trim().isEmpty()) {
            resp.sendRedirect("add_product.jsp?error=Product name is required");
            return;
        }

        ProductsDTO product = new ProductsDTO(name, categoryId, price, description, imageUrl);

        try {
            productBO.saveProduct(product);
            // Redirect to view-product servlet
            resp.sendRedirect("view-product");
        } catch (Exception e) {
            throw new ServletException("Error adding product", e);
        }
    }


}