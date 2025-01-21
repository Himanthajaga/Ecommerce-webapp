package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.ProductBO;

import java.io.IOException;

@WebServlet(name = "DeleteProductServlet", value = "/delete-product")
public class DeleteProductServlet extends HttpServlet {
    private ProductBO productBO;

    @Override
    public void init() throws ServletException {
        productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Product);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));

        try {
            productBO.deleteProduct(String.valueOf(productId));
            response.sendRedirect("view-product");
        } catch (Exception e) {
            throw new ServletException("Error deleting product", e);
        }
    }
}

