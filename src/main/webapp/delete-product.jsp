<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Delete Product</h2>
    <form action="deleteProduct" method="post">
        <div class="mb-3">
            <label for="id" class="form-label">Product ID</label>
            <input type="number" class="form-control" id="id" name="id" required>
        </div>
        <button type="submit" class="btn btn-danger">Delete Product</button>
    </form>
</div>
</body>
</html>