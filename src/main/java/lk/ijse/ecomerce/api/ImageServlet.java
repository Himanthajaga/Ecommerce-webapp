package lk.ijse.ecomerce.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.ProductBO;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ImageServlet", value = "/image")
public class ImageServlet extends HttpServlet {
    private ProductBO productBO;

    @Override
    public void init() throws ServletException {
        productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Product);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productId = request.getParameter("product_id");
        // Retrieve image bytes from the database using productId
        byte[] imageData = productBO.getImageById(productId);

        response.setContentType("image/jpeg"); // or appropriate image type
        OutputStream out = response.getOutputStream();
        out.write(imageData);
        out.close();
    }
}
