<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.CategoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<button class="btn btn-primary" onclick="openAddCategoryForm()">Add Category</button>
<button class="btn btn-primary" onclick="openEditCategoryForm()">Edit Category</button>
<button class="btn btn-primary" onclick="openDeleteCategoryForm()">Delete Category</button>
<div class="container mt-5">
    <h2>Category List</h2>
    <div class="row">
        <%
            List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
            if (categoryList != null && !categoryList.isEmpty()) {
                for (CategoryDTO category : categoryList) {
        %>
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title"><%= category.getName() %></h5>
                    <p class="card-text"><%= category.getDescription() %></p>
                    <a href="editCategory?id=<%= category.getCategory_id() %>" class="btn btn-warning">Edit</a>
                    <a href="deleteCategory?id=<%= category.getCategory_id() %>" class="btn btn-danger">Delete</a>
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
    function openAddCategoryForm() {
        window.location.href = 'add_category.jsp';
    }

    function openEditCategoryForm() {
        window.location.href = 'edit_category.jsp';
    }

    function openDeleteCategoryForm() {
        window.location.href = 'delete_category.jsp';
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>