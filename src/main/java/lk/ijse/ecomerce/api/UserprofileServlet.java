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
import lk.ijse.ecomerce.entity.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserprofileServlet", urlPatterns = "/userprofile")
public class UserprofileServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private UserBO userBO;

    @Override
    public void init() throws ServletException {
        userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.User);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> users = userBO.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/user-profile.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving users", e);
        }
    }
}
