package lk.ijse.ecomerce.bo.custom.impl;

import lk.ijse.ecomerce.bo.custom.ProductBO;
import lk.ijse.ecomerce.dao.DAOFactory;
import lk.ijse.ecomerce.dao.custom.ProductDAO;
import lk.ijse.ecomerce.dto.ProductsDTO;
import lk.ijse.ecomerce.entity.Product;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    private final ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Products);

    public ProductBOImpl(DataSource dataSource) {

    }

    @Override
    public ArrayList<ProductsDTO> getAllProducts() throws Exception {
        ArrayList<Product> allProducts = productDAO.getAll();
        ArrayList<ProductsDTO> dtos = new ArrayList<>();
        for (Product product : allProducts) {
            dtos.add(new ProductsDTO(product.getProduct_id(), product.getName(), product.getCategory_id(), product.getPrice(), product.getDescription(), product.getImage_url()));
        }
//        System.out.println(dtos);
        return dtos;
    }

    @Override
    public boolean saveProduct(ProductsDTO product) throws Exception {
        if (product == null || product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        return productDAO.add(new Product(product.getName(), product.getCategory_id(), product.getPrice(), product.getDescription(), product.getImage_url()));
    }


    @Override
    public boolean updateProduct(ProductsDTO product) throws Exception {
        if (product == null || product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        return productDAO.update(new Product(product.getProduct_id(),product.getName(), product.getCategory_id(), product.getPrice(), product.getDescription(), product.getImage_url()));
    }

    @Override
    public boolean deleteProduct(String productId) throws Exception {
        productDAO.delete(String.valueOf(Integer.parseInt(productId)));
        return false;
    }

    @Override
    public ProductsDTO getProduct(String productID) throws Exception {
        throw new UnsupportedOperationException("Method getProduct is not implemented yet.");
    }

    @Override
    public ProductsDTO getProductById(int productId) throws SQLException, ClassNotFoundException {
        Product product = productDAO.search(String.valueOf(productId));
        if (product != null) {
            return new ProductsDTO(product.getProduct_id(), product.getName(), product.getCategory_id(), product.getPrice(), product.getDescription(), product.getImage_url());
        }
        return null;
    }

    @Override
    public ProductsDTO getProductsByNames(String name) throws SQLException, ClassNotFoundException {
        Product product = productDAO.searchByName(name);
        if (product != null) {
            return new ProductsDTO(product.getProduct_id(),product.getCategory_id(), product.getPrice(), product.getDescription(), product.getImage_url());
        }
        return null;

    }

    @Override
    public byte[] getImageById(String productId) {
        return new byte[0];
    }
}
