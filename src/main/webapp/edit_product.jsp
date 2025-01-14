<%@ page import="lk.ijse.ecomerce.ProductsDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Edit Product</h2>
    <%
        ProductsDTO product = (ProductsDTO) request.getAttribute("product");
        if (product != null) {
    %>
    <form action="editProduct" method="post">
        <input type="hidden" name="id" value="<%= product.getProduct_id() %>">
        <div class="mb-3">
            <label for="name" class="form-label">Product Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= product.getName() %>" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" required><%= product.getDescription() %></textarea>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="<%= product.getPrice() %>" required>
        </div>
        <div class="mb-3">
            <label for="category_id" class="form-label">Category ID</label>
            <input type="number" class="form-control" id="category_id" name="category_id" value="<%= product.getCategory_id() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Update Product</button>
    </form>
    <%
    } else {
    %>
    <p>Product not found.</p>
    <%
        }
    %>
</div>
</body>
</html>