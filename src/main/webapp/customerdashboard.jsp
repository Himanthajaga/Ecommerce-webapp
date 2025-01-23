<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body>
<header>
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
                        <a class="nav-link" href="update-customer.jsp">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div class="container mt-5">
    <h2>Welcome to Your Dashboard</h2>
    <p>Use the navigation bar to access different features.</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>