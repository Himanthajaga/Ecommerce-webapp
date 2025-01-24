<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet"href="css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-dark border-2 mb-4 position-fixed top-0 z-3 w-100">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-primary border-2 logo" href="#">E-Tronics</a>
        <button id="burger-btn" class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item me-3">
                    <a class="nav-link active" aria-current="page" href="admin_dashboard.jsp">Home</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link " href="view-product">Products</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="view-category">Categories</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="viewOrders">Orders</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="viewUsers">Users</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="admin">profile</a>
                </li>
                <li class="nav-item ms-auto">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5 pt-5 text-center fade-in" style="color: #0b3ec1; transition: color 0.5s;">
    <h2>Welcome to Your Admin Dashboard</h2>
    <p>Shopping anything you want.</p>
</div>
<div id="adminCarousel" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#adminCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#adminCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#adminCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="Assests/worldshop-2407-multi-product-apple-tv-4k-3rd-gen-macbook-air-15-m3-chip-ipad-10th-gen-airpods-3rd-gen-iphone-15-plus-iphone-15-apple-watch-se-screen.webp" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <h5>Shop Faster</h5>
                <p>Apple's flagship smartphone, known for its sleek design, powerful performance.</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="Assests/ab01e2267f6af5b93365dfde3f279d1c.png" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <h5>All your needs</h5>
                <p>A line of laptops that includes the MacBook Air and MacBook Pro.</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="Assests/Multi-Product_Apple_TV_4K_3rd-Gen_MacBook_Pro_14-in_iPad_Pro_11-in_Apple_Pencil_2nd-gen_AirPods_Pro_2nd-Gen_iPhone_14_Pro_Max_iPhone_14_Pro_Apple_Wa.webp" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <h5>Moving with technology</h5>
                <p>A smartwatch that offers health and fitness tracking, notifications, and various apps.</p>
            </div>
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#adminCarousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#adminCarousel" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
<footer class="bg-dark text-white pt-4 slide-in-up">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h5 class="text-primary">About Us</h5>
                <p>We are a leading e-commerce platform providing a wide range of products to our customers.</p>
            </div>
            <div class="col-md-4">
                <h5 class="text-primary">Quick Links</h5>
                <ul class="list-unstyled">
                    <ul class="list-unstyled">
                        <li><a href="view-product" class="text-white">Products</a></li>
                        <li><a href="view-category" class="text-white">Categories</a></li>
                        <li><a href="viewOrders" class="text-white">Orders</a></li>
                        <li><a href="viewUsers" class="text-white">Users</a></li>
                        <li><a href="admin" class="text-white">Profile</a></li>
                    </ul>
                </ul>
            </div>
            <div class="col-md-4">
                <h5 class="text-primary">Follow Us</h5>
                <a href="#" class="text-white me-3"><i class="fab fa-facebook-f"></i></a>
                <a href="#" class="text-white me-3"><i class="fab fa-twitter"></i></a>
                <a href="#" class="text-white me-3"><i class="fab fa-instagram"></i></a>
                <a href="#" class="text-white"><i class="fab fa-linkedin-in"></i></a>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col text-center">
                <p>&copy; 2025 E-Commerce. All rights reserved.</p>
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>