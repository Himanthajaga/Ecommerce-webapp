package lk.ijse.ecomerce.dao.custom;

import lk.ijse.ecomerce.dao.CrudDAO;
import lk.ijse.ecomerce.entity.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO extends CrudDAO<Category> {

    List<Integer> getAllCategoryIds() throws SQLException;
}
