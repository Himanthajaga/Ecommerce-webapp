package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.dto.UserDTO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewUsersServlet", urlPatterns = "/viewUsers")
public class ViewUsersServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> userList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE role = 'customer'");
            while (rs.next()) {
                UserDTO userDTO = new UserDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
                userList.add(userDTO);
            }
            req.setAttribute("userList", userList);
            req.getRequestDispatcher("view_users.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("admin_dashboard.jsp?error=Failed to load users");
        }
    }
}