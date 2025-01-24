<%@ page import="lk.ijse.ecomerce.dto.CartDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet"href="css/style.css">

    <style>
        .btn-secondary {
            background-color: #000000; /* Primary color */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out;
        }
        .btn-primary {
            background-color: #000000; /* Primary color */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out;
        }
        .btn-danger {
            margin-bottom: 11px;
            background-color: #0b3ec1; /* Matching the primary color */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            /*margin: 4px 2px;*/
            cursor: pointer;
            transition: background-color 0.3s ease-in-out;
        }

        .btn-danger:hover {
            background-color: #ffcc00; /* Matching the hover color */
            color: #0b3ec1;
        }

        /* Style for Delete button */
        .btn-success {
            background-color: #000000; /* Matching the secondary color */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out;
        }

        .btn-success:hover {
            background-color: #ffcc00; /* Matching the hover color */
            color: #000000;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-dark border-2 mb-4 position-fixed top-0 z-3 w-100">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-primary border-2 logo" href="#">E-Tronics</a>
        <button id="burger-btn" class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item me-3">
                    <a class="nav-link " aria-current="page" href="customerdashboard.jsp">Home</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="customerproducts">Browse Products</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link active" href="shopping-cart.jsp">Shopping Cart</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="order-history">Order History</a>
                </li>
                <li class="nav-item  me-3">
                    <a class="nav-link" href="customer">Profile</a>
                </li>
                <li class="nav-item ms-auto">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5 pt-5  fade-in" style="color: #0b3ec1; transition: color 0.5s;">
    <h2 class="text-center">Shopping Cart</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Product Image</th>
            <th>Product Name</th>
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
            <td><img src="<%= item.getImage_url() %>" alt="Product Image" width="100" height="100"></td>
            <td><%= item.getProduct_name() %></td>
            <td><%= item.getQuantity() %></td>
            <td>$<%= item.getPrice() %></td>
            <td>$<%= item.getTotal() %></td>
            <td>

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
<footer class="bg-dark text-white pt-4 slide-in-up">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h5 class="text-primary">About Us</h5>
                <p>We are a leading e-commerce platform providing a wide range of products to our customers.</p>
            </div>
            <div class="col-md-4">
                <h5 class="text-primary">Quick Links</h5>
                <ul class="list-unstyled">
                    <li><a href="customerdashboard.jsp" class="text-white">Home</a></li>
                    <li><a href="customerproducts" class="text-white">Browse Products</a></li>
                    <li><a href="shopping-cart.jsp" class="text-white">Shopping Cart</a></li>
                    <li><a href="order-history" class="text-white">Order History</a></li>
                    <li><a href="customer" class="text-white">Profile</a></li>
                </ul>
            </div>
            <div class="col-md-4">
                <h5 class="text-primary">Follow Us</h5>
                <a href="#" class="text-white me-3"><i class="fab fa-facebook-f"></i></a>
                <a href="#" class="text-white me-3"><i class="fab fa-twitter"></i></a>
                <a href="#" class="text-white me-3"><i class="fab fa-instagram"></i></a>
                <a href="#" class="text-white"><i class="fab fa-linkedin-in"></i></a>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col text-center">
                <p>&copy; 2025 E-Commerce. All rights reserved.</p>
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>