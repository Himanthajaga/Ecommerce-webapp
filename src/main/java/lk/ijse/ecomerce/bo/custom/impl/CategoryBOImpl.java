package lk.ijse.ecomerce.bo.custom.impl;

import lk.ijse.ecomerce.bo.custom.CategoryBO;
import lk.ijse.ecomerce.dao.custom.CategoryDAO;
import lk.ijse.ecomerce.dao.custom.impl.CategoryDAOImpl;
import lk.ijse.ecomerce.dto.CategoryDTO;
import lk.ijse.ecomerce.entity.Category;
import lk.ijse.ecomerce.entity.Product;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {
    private final CategoryDAO categoryDAO;

    public CategoryBOImpl(DataSource dataSource) {
        this.categoryDAO = new CategoryDAOImpl(dataSource);
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws SQLException, ClassNotFoundException {
        System.out.println("getAllCategories method called");
        List<CategoryDTO> categories = categoryDAO.getAll().stream().map(category -> new CategoryDTO(category.getCategory_id(), category.getName(), category.getDescription())).toList();
        System.out.println("Categories: " + categories);
        return categories;
    }

    @Override
    public boolean addCategory(CategoryDTO category) throws SQLException, ClassNotFoundException {
        return categoryDAO.add(new Category(category.getName(), category.getDescription()));
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) throws SQLException, ClassNotFoundException {
        Category category = categoryDAO.search(String.valueOf(categoryId));
        if (category!= null) {
            return new CategoryDTO(category.getCategory_id(), category.getName(), category.getDescription());
        }
        return null;
    }

    @Override
    public boolean updateCategory(CategoryDTO category) throws SQLException, ClassNotFoundException {
        if (category == null || category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        return categoryDAO.update(new Category(category.getCategory_id(), category.getName(), category.getDescription()));
    }

    @Override
    public boolean deleteCategory(String id) throws SQLException, ClassNotFoundException {
        return categoryDAO.delete(id);
    }
}