package lk.ijse.ecomerce.api;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.ecomerce.bo.custom.UserBO;
import lk.ijse.ecomerce.bo.custom.impl.UserBOImpl;
import lk.ijse.ecomerce.entity.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource dataSource;

    private UserBO userBO;

    @Override
    public void init() throws ServletException {
        userBO = new UserBOImpl(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); // Delegate to doPost method
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list"; // Default action

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
        try {
            List<User> userList = userBO.getAllUsers();
            req.setAttribute("users", userList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("user-list.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException | ClassNotFoundException | ServletException e) {
            e.printStackTrace();
            resp.sendRedirect("user-list.jsp?error=Failed to load users");
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        try {
            userBO.deleteUser(id);
            resp.sendRedirect("user-delete.jsp?message=User Deleted Successfully...!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendRedirect("user-delete.jsp?error=Failed to delete user...!");
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String role = req.getParameter("role");
            int active = Integer.parseInt(req.getParameter("active"));

            User user = new User(username, password, email, role, active);
            userBO.updateUser(user);
            resp.sendRedirect("user-update.jsp?message=User Updated Successfully...!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendRedirect("user-update.jsp?error=Failed to update user...!");
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        int active = Integer.parseInt(req.getParameter("active"));

        User user = new User(username, password, email, role, active);

        try {
            userBO.addUser(user);
            resp.sendRedirect("user-save.jsp?message=User Saved Successfully...!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendRedirect("user-save.jsp?error=Failed to save user...!");
        }
    }
}