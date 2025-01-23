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
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/customerproducts")
public class ProductServlet extends HttpServlet {
    private ProductBO productBO;

    @Override
    public void init() throws ServletException {
        productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Product);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ProductsDTO> productList = productBO.getAllProducts();
            request.setAttribute("Cusproducts", productList);
            request.getRequestDispatcher("View-products.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving products", e);
        }
    }
}