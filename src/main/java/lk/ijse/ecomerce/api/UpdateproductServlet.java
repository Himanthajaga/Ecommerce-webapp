package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.ProductBO;
import lk.ijse.ecomerce.dto.ProductsDTO;

import java.io.IOException;

@WebServlet(name = "UpdateproductServlet", value = "/update-product")
public class UpdateproductServlet extends HttpServlet {
    private ProductBO productBO;

    @Override
    public void init() throws ServletException {
        productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Product);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        String name = request.getParameter("name");
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("image_url");

        ProductsDTO product = new ProductsDTO(productId,name,categoryId, price, description, imageUrl);

        try {
            productBO.updateProduct(product);
            response.sendRedirect("view-product");
        } catch (Exception e) {
            throw new ServletException("Error updating product", e);
        }
    }
}