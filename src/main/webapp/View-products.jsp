<%@ page import="lk.ijse.ecomerce.dto.ProductsDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Browse Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
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
                    <a class="nav-link" href="customer">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5">
    <h2>Browse Products</h2>
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
            List<ProductsDTO> products = (List<ProductsDTO>) request.getAttribute("Cusproducts");
            if (products != null) {
                for (ProductsDTO product : products) {
        %>
        <div class="col-md-4" data-category="<%= product.getCategory_id() %>">
            <div class="card mb-4 shadow-sm">
                <img src="<%= product.getImage_url() %>" class="card-img-top" alt="Product Image" style="display: block; margin-left: auto; margin-right: auto; max-width: 50%; max-height: 100%; object-fit: cover;">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <p class="card-text">Price: $<%= product.getPrice() %></p>
                    <p class="card-text"><%= product.getDescription() %></p>
                    <div class="d-flex justify-content-between align-items-center">
                        <form action="add-to-cart" method="post">
                            <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="price" value="<%= product.getPrice() %>">
                            <button type="submit" class="btn btn-success">Add to Cart</button>
                        </form>
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
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>