package lk.ijse.ecomerce.bo.custom;

import lk.ijse.ecomerce.bo.SuperBO;
import lk.ijse.ecomerce.dto.CategoryDTO;

import java.sql.SQLException;
import java.util.List;

public interface CategoryBO extends SuperBO {
    List<CategoryDTO> getAllCategories() throws SQLException, ClassNotFoundException;

    boolean deleteCategory(String id) throws SQLException, ClassNotFoundException;

    boolean updateCategory(CategoryDTO category) throws SQLException, ClassNotFoundException;

    boolean addCategory(CategoryDTO category) throws SQLException, ClassNotFoundException;
    CategoryDTO getCategoryById(int categoryId) throws SQLException, ClassNotFoundException;
}
