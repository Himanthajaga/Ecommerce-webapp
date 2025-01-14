<%@ page import="lk.ijse.ecomerce.CategoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Edit Category</h2>
    <%
        CategoryDTO category = (CategoryDTO) request.getAttribute("category");
        if (category != null) {
    %>
    <form action="editCategory" method="post">
        <input type="hidden" name="id" value="<%= category.getCategory_id() %>">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= category.getName() %>" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" required><%= category.getDescription() %></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
    <%
    } else {
    %>
    <p>Category not found.</p>
    <%
        }
    %>
</div>
</body>
</html>