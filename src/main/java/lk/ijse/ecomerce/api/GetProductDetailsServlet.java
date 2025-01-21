package lk.ijse.ecomerce.api;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.ProductBO;
import lk.ijse.ecomerce.dto.ProductsDTO;

import java.io.IOException;

@WebServlet(name = "GetProductDetailsServlet", value = "/get-product-details")
public class GetProductDetailsServlet extends HttpServlet {
    private ProductBO productBO;

    @Override
    public void init() throws ServletException {
        productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Product);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int productId = Integer.parseInt(request.getParameter("product_id"));
            try {
                ProductsDTO product = productBO.getProductById(productId);
                response.setContentType("application/json");
                response.getWriter().write(new Gson().toJson(product));
            } catch (Exception e) {
                throw new ServletException("Error retrieving product details", e);
            }
        }
    }
