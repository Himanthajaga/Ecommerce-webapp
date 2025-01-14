package lk.ijse.ecomerce;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";// Default action

        System.out.println(action);

        switch (action) {
            case "create":
                createCategory(req, resp);
                break;
            case "update":
                try {
                    updateCategory(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                deleteCategory(req, resp);
                break;
            default:
                listCategories(req, resp);
                break;
        }
    }

    private void listCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<CategoryDTO> categoryList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            ResultSet resultSet = connection.prepareStatement("select * from category").executeQuery();
            while (resultSet.next()) {
                CategoryDTO categoryDTO = new CategoryDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)

                );
                categoryList.add(categoryDTO);
            }

            req.setAttribute("Category", categoryList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("category.jsp");

            requestDispatcher.forward(req, resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
            resp.sendRedirect("category.jsp?error=Failed to load products");
        }
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM category WHERE id=?");
            pstm.setString(1, id);
            int i = pstm.executeUpdate();
            pstm.close();
            connection.close();

            if (i > 0) {
                //success
                resp.sendRedirect("category.jsp?message=Category Deleted Successfully...!");
            } else {
                //fail
                resp.sendRedirect("category.jsp?error=Category Delete Failed...!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.sendRedirect("category.jsp?error=Failed to delete Category...!");
        }
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Connection connection = dataSource.getConnection();
       String category_id = req.getParameter("category_id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");


        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE category SET name=?, description=? WHERE category_id=?");
            pstm.setString(1, name);
            pstm.setString(2, description);
            pstm.setString(3, category_id);
            int i = pstm.executeUpdate();
            pstm.close();
            connection.close();
            if (i > 0) {
                //success
                resp.sendRedirect("category.jsp?message=Category Updated Successfully...!");
            } else {
                //fail
                resp.sendRedirect("category.jsp?error=Category Update Failed...!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.sendRedirect("category.jsp?error=Failed to update Category...!");
        }
    }

    private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        try {
            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO category (name, description) VALUES (?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(2, name);
            pstm.setString(3, description);

            int i = pstm.executeUpdate();
            pstm.close();
            connection.close();

            if (i > 0) {
                //success
                resp.sendRedirect("customer-save.jsp?message=Customer Saved Successfully...!");
            } else {
                //fail
                resp.sendRedirect("customer-save.jsp?error=Customer Save Failed...!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.sendRedirect("customer-save.jsp?error=Failed to save customer...!");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
