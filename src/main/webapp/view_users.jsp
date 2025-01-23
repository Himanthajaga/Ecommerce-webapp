<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">E-Commerce</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="view-product">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="category.jsp">Categories</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="viewOrders">Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="viewUsers">Users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5">
    <h1>User List</h1>
    <%
        List<UserDTO> userDataList = (List<UserDTO>) request.getAttribute("userList");
        if (userDataList != null && userDataList.size() > 0){
    %>
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Active</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (UserDTO user : userDataList){
        %>
        <tr>
            <td><%= user.getUserId() %></td>
            <td><%= user.getUserName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getRole() %></td>
            <td><%= user.isActive() %></td>
            <td>
                <form action="updateUserStatus" method="post">
                    <input type="hidden" name="user_id" value="<%= user.getUserId() %>">
                    <input type="hidden" name="is_active" value="<%= !user.isActive() %>">
                    <button type="submit" class="btn btn-primary"><%= user.isActive() ? "Deactivate" : "Activate" %></button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>
    <p>No users found.</p>
    <%
        }
    %>
</div>
</body>
</html>