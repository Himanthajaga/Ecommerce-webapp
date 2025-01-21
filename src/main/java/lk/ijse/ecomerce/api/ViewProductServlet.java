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

@WebServlet(name = "ViewProductServlet", value = "/view-product")
public class ViewProductServlet extends HttpServlet {
    private ProductBO productBO;

@Override
    public void init() throws ServletException{
        productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Product);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ProductsDTO> productList = productBO.getAllProducts();
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("product-list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving products", e);
        }
    }
}