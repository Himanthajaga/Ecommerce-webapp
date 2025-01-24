<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.ProductsDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet"href="css/style.css">
</head>
<script>
    function loadProductDetails() {
        var productId = document.getElementById("productSelect").value;
        if (productId) {
            fetch('get-product-details?product_id=' + productId)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("product_id").value = data.product_id;
                    document.getElementById("name").value = data.name;
                    document.getElementById("category_id").value = data.category_id;
                    document.getElementById("price").value = data.price;
                    document.getElementById("description").value = data.description;
                    document.getElementById("image_url").value = data.image_url;
                });
        }
    }
</script>
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
<div class="container mt-5 pt-5  fade-in" style="color: #0b3ec1; transition: color 0.5s;">
    <h2 class="text-center">Edit Product</h2>
    <form action="update-product" method="post">
        <div class="mb-3">
            <label for="productSelect" class="form-label">Select Product</label>
            <select id="productSelect" class="form-select" name="product_id" onchange="loadProductDetails()">
                <option value="">Select a product</option>
                <%
                    List<ProductsDTO> productList = (List<ProductsDTO>) request.getAttribute("productList");
                    if (productList != null && !productList.isEmpty()) {
                        for (ProductsDTO product : productList) {
                %>
                <option value="<%= product.getProduct_id() %>"><%= product.getName() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="mb-3">
            <label for="product_id" class="form-label">Product ID</label>
            <input type="text" class="form-control" id="product_id" name="product_id" readonly>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="mb-3">
            <label for="category_id" class="form-label">Category ID</label>
            <input type="text" class="form-control" id="category_id" name="category_id">
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="text" class="form-control" id="price" name="price">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description"></textarea>
        </div>
        <div class="mb-3">
            <label for="image_url" class="form-label">Image URL</label>
            <input type="file" class="form-control" id="image_url" name="image_url">
        </div>
        <button type="submit" class="btn btn-primary">Update Product</button>
    </form>
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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>