package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.BOFactory;
import lk.ijse.ecomerce.bo.custom.UserBO;
import lk.ijse.ecomerce.dto.UserDTO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdateUserServlet.class.getName());
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private UserBO userBO;

    @Override
    public void init() throws ServletException {
        userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.User);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));

        UserDTO user = new UserDTO(userName, password, email, role, active);
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE users SET user_name = ?, password = ?, email = ?, role = ?, active = ? WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getRole());
                statement.setBoolean(5, user.isActive());
                statement.setInt(6, user.getUserId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            throw new ServletException("Database error", e);
        }

        response.sendRedirect("customer");
    }
}