<%@ page import="lk.ijse.ecomerce.dto.CartDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
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
                    <a class="nav-link" href="user-profile.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2>Shopping Cart</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
            if (cart != null) {
                for (CartDTO item : cart) {
        %>
        <tr>
            <td><%= item.getProduct_id() %></td>
            <td><%= item.getQuantity() %></td>
            <td>$<%= item.getPrice() %></td>
            <td>$<%= item.getTotal() %></td>
            <td>
<%--                <a href="update_cart.jsp?product_id=<%= item.getProductId() %>" class="btn btn-primary">Update</a>--%>
    <a href="remove-from-cart?product_id=<%= item.getProduct_id() %>" class="btn btn-danger">Remove</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5">Your cart is empty.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <a href="checkout.jsp" class="btn btn-success">Checkout</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>