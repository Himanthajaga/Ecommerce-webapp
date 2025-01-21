<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.ProductsDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
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
                    <a class="nav-link" href="view-category">Categories</a>
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
<button type="button" class="btn btn-primary" onclick="window.location.href='add_product.jsp'">Add New Product</button>
<div class="container mt-5">
    <h2>Product List</h2>
    <div class="row">
        <%
            List<ProductsDTO> productList = (List<ProductsDTO>) request.getAttribute("productList");
            if (productList != null && !productList.isEmpty()) {
                for (ProductsDTO product : productList) {
        %>
        <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
                <img src="<%= product.getImage_url() %>" class="card-img-top" alt="Product Image">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <p class="card-text">Price: $<%= product.getPrice() %></p>
                    <p class="card-text"><%= product.getDescription() %></p>
                    <div class="d-flex justify-content-between align-items-center">
                        <a href="add_to_cart.jsp" class="btn btn-primary">Add to Cart</a>
                        <a href="edit-product?product_id=<%= product.getProduct_id() %>" class="btn btn-secondary">Edit Product</a>
                        <form action="delete-product" method="post" style="display:inline;">
                            <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
                            <button type="submit" class="btn btn-danger" onclick="confirmDelete(1)">
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
<script>
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