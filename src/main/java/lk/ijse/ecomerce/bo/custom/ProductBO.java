package lk.ijse.ecomerce.bo.custom;

import lk.ijse.ecomerce.bo.SuperBO;
import lk.ijse.ecomerce.dto.ProductsDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductBO extends SuperBO {
    List<ProductsDTO> getAllProducts() throws Exception;
    boolean saveProduct(ProductsDTO product) throws Exception;
    boolean updateProduct(ProductsDTO product) throws Exception;
    boolean deleteProduct(String productID) throws Exception;
    ProductsDTO getProduct(String productID) throws Exception;

    ProductsDTO getProductById(int productId) throws SQLException, ClassNotFoundException;
    ProductsDTO getProductsByNames(String name) throws SQLException, ClassNotFoundException;

    byte[] getImageById(String productId);
}
