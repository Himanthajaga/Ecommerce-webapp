<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.ProductsDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card {
            margin-bottom: 20px;
        }
        img {
            height: 20px;
            width: 20px;
            object-fit: cover;
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
                    <a class="nav-link" href="viewProducts">Products</a>
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
            </ul>
        </div>
    </div>
</nav>
<button class="btn btn-primary" onclick="openAddProductForm()">Add Product</button>
<button class="btn btn-primary" onclick="openEditProductForm()">Edit Product</button>
<button class="btn btn-primary" onclick="openDeleteProductForm()">Delete Product</button>
<div class="container mt-5">
    <h2>Product List</h2>
    <div class="row">
        <%
            List<ProductsDTO> productList = (List<ProductsDTO>) request.getAttribute("productList");
            if (productList != null && !productList.isEmpty()) {
                for (ProductsDTO product : productList) {
        %>
        <div class="col-md-4">
            <div class="card mb-4">
                <img src="<%= product.getImage_url() %>" class="card-img-top" alt="Product Image">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <p class="card-text">Price: $<%= product.getPrice() %></p>
                    <p class="card-text"><%= product.getDescription() %></p>
                    <a href="add_to_cart.jsp" class="btn btn-primary">Add to Cart</a>
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
<script>
    function openAddProductForm() {
        window.location.href = 'add_product.jsp';
    }

    function openEditProductForm() {
        window.location.href = 'edit_product.jsp';
    }

    function openDeleteProductForm() {
        window.location.href = 'delete_product.jsp';
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>