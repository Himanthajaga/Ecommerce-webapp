<%@ page import="lk.ijse.ecomerce.dto.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.OrderDetailDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Orders</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
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
<div class="container mt-5">
    <h1>Order History</h1>
    <%
        List<OrderDTO> orderList = (List<OrderDTO>) request.getAttribute("Allorders");
        if (orderList != null && orderList.size() > 0){
    %>
    <table border="1">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Total Amount</th>
            <th>Status</th>
            <th>Payment Method</th>
            <th>Discount</th>
            <th>Items</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (OrderDTO order : orderList){
        %>
        <tr>
            <td><%= order.getOrder_id() %></td>
            <td><%= order.getOrder_date() %></td>
            <td><%= order.getTotalAmount() %></td>
            <td><%= order.getStatus() %></td>
            <td><%= order.getPaymentMethod() %></td>
            <td><%= order.getDiscount() %></td>
            <td>
                <ul>
                    <%
                        for (OrderDetailDTO item : order.getOrderItems()){
                    %>
                    <li>Product ID: <%= item.getProductId() %>, Quantity: <%= item.getQuantity() %>, Price: <%= item.getPrice() %></li>
                    <%
                        }
                    %>
                </ul>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>
    <p>No orders found.</p>
    <%
        }
    %>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>