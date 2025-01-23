<%--
  Created by IntelliJ IDEA.
  User: jgkin
  Date: 1/23/2025
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function fillCustomerDetails() {
            var customers = ${customers};
            var selectedName = document.getElementById("customerName").value;
            var customer = customers.find(c => c.name === selectedName);
            if (customer) {
                document.getElementById("email").value = customer.email;
                document.getElementById("password").value = customer.password;
                document.getElementById("Role").value = customer.Role;
                document.getElementById("active").value = customer.active;
            }
        }
    </script>
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
                        <a class="nav-link" href="update-customer.jsp">Profile</a>
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
    <form action="updateUser" method="post">
        <div class="form-group">
            <label for="customerName">Customer Name</label>
            <select id="customerName" name="customerName" class="form-control" onchange="fillCustomerDetails()">
                <option value="">Select Customer</option>
                <c:forEach var="customer" items="${customers}">
                    <option value="${customer.name}">${customer.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="text" id="password" name="password" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="Role">Role</label>
            <input type="text" id="Role" name="Role" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="active">Active</label>
            <input type="text" id="active" name="active" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
