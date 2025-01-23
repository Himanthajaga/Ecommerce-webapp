<%@ page import="lk.ijse.ecomerce.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-light bg-success border-1 mb-4 position-fixed top-0 z-3 w-100">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold text-primary border-2 logo" href="#">E-Commerce</a>
            <button id="burger-btn" class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="customerdashboard.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="customerproducts">Browse Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="shopping-cart.jsp">Shopping Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="order-history">Order History</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="customer">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<div class="container mt-5">
    <h2>Update Customer</h2>
    <form action="customer" method="post">
        <%
            UserDTO customer = (UserDTO) request.getAttribute("customer");
            if (customer != null) {
        %>
        <input type="hidden" name="userId" value="<%= customer.getUserId() %>">
        <div class="form-group">
            <label for="userName">User Name</label>
            <input type="text" id="userName" name="userName" class="form-control" value="<%= customer.getUserName() %>" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" class="form-control" value="<%= customer.getEmail() %>" required>
        </div>
        <div class="form-group">
            <label for="role">Role</label>
            <input type="text" id="role" name="role" class="form-control" value="<%= customer.getRole() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
        <%
        } else {
        %>
        <p>No customer data found.</p>
        <%
            }
        %>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>