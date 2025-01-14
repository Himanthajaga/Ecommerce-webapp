<%--
  Created by IntelliJ IDEA.
  User: jgkin
  Date: 1/12/2025
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Manage</title>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
    />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
    />
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"
    />
</head>
<body>
<header>
    <nav
            class="navbar navbar-expand-md  navbar bg-success data-bs-theme=dark border-1  mb-4 position-fixed top-0 z-3 w-100"
    >
        <div class="container-fluid">
            <!--            <a class="navbar-brand fw-bold text-primary border-2 logo" href="#">-->
            <!--&lt;!&ndash;                <img&ndash;&gt;-->
            <!--&lt;!&ndash;                        src="assets/images/posLogo.png"&ndash;&gt;-->
            <!--&lt;!&ndash;                        alt="POS Logo"&ndash;&gt;-->
            <!--&lt;!&ndash;                        class="d-inline-block align-text-top"&ndash;&gt;-->
            <!--&lt;!&ndash;                />&ndash;&gt;-->
            <!--                POS System-->
            <!--            </a>-->
            <button
                    id="burger-btn"
                    class="navbar-toggler border-0"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav

                ">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="customerSave.jsp">Add New Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="customer-all">View Customers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="customer-delete.jsp">Delete Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="customer-edit.jsp">Edit Customer</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
.
<%----%>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
