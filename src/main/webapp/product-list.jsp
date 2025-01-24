<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.ProductsDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
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
        .btn-info {
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

        .btn-info:hover {
            background-color: #ffcc00; /* Matching the hover color */
            color: #000000;
        }
    </style>
</head>
<body onload="filterProducts('All')">
<nav class="navbar navbar-expand-md navbar-light bg-dark border-2 mb-4 position-fixed top-0 z-3 w-100">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-primary border-2 logo" href="#">E-Tronics</a>
        <button id="burger-btn" class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item me-3">
                    <a class="nav-link " aria-current="page" href="admin_dashboard.jsp">Home</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link active" href="view-product">Products</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="view-category">Categories</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="viewOrders">Orders</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="viewUsers">Users</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="admin">profile</a>
                </li>
                <li class="nav-item ms-auto">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5 pt-5 text-center">
    <button type="button" class="btn btn-primary mb-4" onclick="window.location.href='add_product.jsp'">Add New Product</button>
    <div class="mt-5 pt-5 text-center fade-in" style="color: #0b3ec1; transition: color 0.5s;">
        <h2>Product List</h2>
        <div class="btn-group mb-3" role="group" aria-label="Category buttons">
            <button type="button" class="btn btn-primary" onclick="filterProducts('1')">TV</button>
            <button type="button" class="btn btn-primary" onclick="filterProducts('2')">Mobile Phones</button>
            <button type="button" class="btn btn-primary" onclick="filterProducts('3')">Speakers</button>
            <button type="button" class="btn btn-primary" onclick="filterProducts('4')">Smart Watches</button>
            <button type="button" class="btn btn-primary" onclick="filterProducts('5')">Ipads</button>
            <button type="button" class="btn btn-secondary" onclick="filterProducts('All')">All</button>
        </div>
        <div class="row" id="product-list">
            <%-- Product cards will be here --%>
            <%
                List<ProductsDTO> products = (List<ProductsDTO>) request.getAttribute("productList");
                if (products != null) {
                    for (ProductsDTO product : products) {
            %>
            <div class="col-md-4" data-category="<%= product.getCategory_id() %>">
                <div class="card mb-4 shadow-sm">
                    <img src="<%= product.getImage_url() %>" class="card-img-top" alt="Product Image" style="display: block; margin-left: auto; margin-right: auto; max-width: 50%; max-height: 100%; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getName() %></h5>
                        <p class="card-text">Price: $<%= product.getPrice() %></p>
                        <p class="card-text description-text"><%= product.getDescription() %></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <a href="edit-product?product_id=<%= product.getProduct_id() %>" class="btn btn-danger">Edit</a>
                            <form action="delete-product" method="post" style="display:inline;">
                                <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
                                <button type="submit" class="btn btn-info" onclick="confirmDelete(<%= product.getProduct_id() %>)">
                                    <i class="bi bi-trash"></i> Delete
                                </button>
                            </form>
                            <small class="text-muted">Category ID: <%= product.getCategory_id() %></small>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p>No products found.</p>
            <%
                }
            %>
        </div>
    </div>
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
                    <li><a href="view-product" class="text-white">Products</a></li>
                    <li><a href="view-category" class="text-white">Categories</a></li>
                    <li><a href="viewOrders" class="text-white">Orders</a></li>
                    <li><a href="viewUsers" class="text-white">Users</a></li>
                    <li><a href="admin" class="text-white">Profile</a></li>
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
<script>
    function filterProducts(category) {
        var products = document.querySelectorAll('#product-list .col-md-4');
        products.forEach(function(product) {
            if (category === 'All' || product.getAttribute('data-category') === category) {
                product.style.display = 'block';
            } else {
                product.style.display = 'none';
            }
        });
    }

    function confirmDelete(productId) {
        if (confirm("Are you sure you want to delete this product?")) {
            window.location.href = 'delete-product?product_id=' + productId;
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
</body>
</html>