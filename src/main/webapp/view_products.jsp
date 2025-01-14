<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.ProductsDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Product List</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Category ID</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ProductsDTO> productList = (List<ProductsDTO>) request.getAttribute("productList");
            if (productList != null && !productList.isEmpty()) {
                for (ProductsDTO product : productList) {
        %>
        <tr>
            <td><%= product.getProduct_id() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getCategory_id() %></td>
            <td>
                <a href="editProduct?id=<%= product.getProduct_id() %>" class="btn btn-warning">Edit</a>
                <a href="deleteProduct?id=<%= product.getProduct_id() %>" class="btn btn-danger">Delete</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">No products found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>