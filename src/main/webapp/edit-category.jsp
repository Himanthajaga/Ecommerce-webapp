<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.CategoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<script>
    function loadCategoryDetails() {
        var categoryId = document.getElementById("categorySelect").value;
        if (categoryId) {
            fetch('get-category-details?category_id=' + categoryId)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("id").value = categoryId;
                    document.getElementById("name").value = data.name;
                    document.getElementById("description").value = data.description;
                });
        }
    }
</script>
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
<div class="container mt-5">
    <h2>Edit Category</h2>
    <form action="update-category" method="post">
        <input type="hidden" id="id" name="id">
        <div class="mb-3">
            <label for="categorySelect" class="form-label">Select Category</label>
            <select class="form-control" id="categorySelect" name="categorySelect" onchange="loadCategoryDetails()">
                <option value="">Select a category</option>
                <%
                    List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
                    if (categoryList != null) {
                        for (CategoryDTO category : categoryList) {
                %>
                <option value="<%= category.getCategory_id() %>"><%= category.getName() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>