<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.CategoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<button type="button" class="btn btn-primary" onclick="window.location.href='add_category.jsp'">Add New Category</button>
<div class="container mt-5">
    <h2>Category List</h2>
    <div class="row">
        <%
            List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
            if (categoryList != null && !categoryList.isEmpty()) {
                for (CategoryDTO category : categoryList) {
        %>
        <div class="col-md-4">
            <div class="card mb-4 shadow-sm">
                <div class="card-body">
                    <h5 class="card-title"><%= category.getName() %></h5>
                    <p class="card-text"><%= category.getDescription() %></p>
                    <div class="d-flex justify-content-between align-items-center">
                        <a href="edit-category?id=<%= category.getCategory_id() %>" class="btn btn-warning">Edit</a>
                        <a href="delete-category?id=<%= category.getCategory_id() %>" class="btn btn-danger"onclick="confirmDelete(<%=category.getCategory_id()%>)">Delete</a>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p>No categories found.</p>
        <%
            }
        %>
    </div>
</div>
<script>
    function confirmDelete(categoryId) {
        if (confirm("Are you sure you want to delete this category?")) {
            window.location.href = 'delete-category?id=' + categoryId;
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>