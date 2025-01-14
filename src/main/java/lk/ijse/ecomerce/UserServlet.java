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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: JG HIMANTHA
 * Date: 2025-01-12
 * Time: 08:30 PM
 * Description:
 */
@WebServlet(name = "CustomerServlet", value = "/customer")
public class UserServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";// Default action

        System.out.println(action);

        switch (action) {
            case "create":
                createUser(req, resp);
                break;
            case "update":
                updateUser(req, resp);
                break;
            case "delete":
                deleteUser(req, resp);
                break;
            default:
                listUsers(req, resp);
                break;
        }

    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<UserDTO> userList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            ResultSet resultSet = connection.prepareStatement("select * from users").executeQuery();
            while (resultSet.next()) {
                UserDTO userDTO = new UserDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6)
                );
                userList.add(userDTO);
            }

            req.setAttribute("customers", userList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("customer-list.jsp");

            requestDispatcher.forward(req, resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
            resp.sendRedirect("customer-list.jsp?error=Failed to load customers");
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM users WHERE id=?");
            pstm.setString(1, id);
            int i = pstm.executeUpdate();
            pstm.close();
            connection.close();

            if (i > 0) {
                //success
                resp.sendRedirect("customer-delete.jsp?message=Customer Deleted Successfully...!");
            } else {
                //fail
                resp.sendRedirect("customer-delete.jsp?error=Customer Delete Failed...!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.sendRedirect("customer-delete.jsp?error=Failed to delete customer...!");
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Connection connection = dataSource.getConnection();
            String user_id = req.getParameter("user_id");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String role = req.getParameter("role");
            String active = req.getParameter("active");

            String sql = "UPDATE users SET username=?, password=?, email=?, role=?, active=? WHERE id=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setString(4, role);
            pstm.setString(5, active);

            int i = pstm.executeUpdate();
            pstm.close();
            connection.close();

            if (i > 0) {
                //success
                resp.sendRedirect("customer-update.jsp?message=Customer Updated Successfully...!");
            } else {
                //fail
                resp.sendRedirect("customer-update.jsp?error=Customer Update Failed...!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.sendRedirect("customer-update.jsp?error=Failed to update customer...!");
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String active = req.getParameter("active");
        try {
            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO users (username, password, email, role, active) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setString(4, role);
            pstm.setString(5, active);


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
}