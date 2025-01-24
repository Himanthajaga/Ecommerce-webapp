<%@ page import="lk.ijse.ecomerce.dto.CartDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet"href="css/style.css">
    <script>
        function updateTotal() {
            let totalValue = 0;
            document.querySelectorAll('.item-row').forEach(row => {
                const checkbox = row.querySelector('.select-item');
                if (checkbox.checked) {
                    const quantity = parseInt(row.querySelector('.quantity-input').value);
                    const price = parseFloat(row.querySelector('.price').innerText.replace('$', ''));
                    const itemTotal = quantity * price;
                    row.querySelector('.item-total').innerText = '$' + itemTotal.toFixed(2);
                    totalValue += itemTotal;
                } else {
                    row.querySelector('.item-total').innerText = '$0.00';
                }
            });
            const discount = parseFloat(document.getElementById('discount').value) || 0;
            totalValue -= discount;
            document.getElementById('totalAmount').value = totalValue.toFixed(2);
        }
    </script>
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
                    <a class="nav-link " aria-current="page" href="customerdashboard.jsp">Home</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="customerproducts">Browse Products</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link active" href="shopping-cart.jsp">Shopping Cart</a>
                </li>
                <li class="nav-item me-3">
                    <a class="nav-link" href="order-history">Order History</a>
                </li>
                <li class="nav-item  me-3">
                    <a class="nav-link" href="customer">Profile</a>
                </li>
                <li class="nav-item ms-auto">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5 pt-5  fade-in" style="color: #0b3ec1; transition: color 0.5s;">
    <h2 class="text-center">Checkout</h2>
    <form action="purchase-order" method="post">
        <table class="table">
            <thead>
            <tr>
                <th>Select</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<CartDTO> cart = (List<CartDTO>) session.getAttribute("cart");
                Integer userId = (Integer) session.getAttribute("user_id");
                double totalValue = 0;
                if (cart != null) {
                    for (CartDTO item : cart) {
                        double itemTotal = item.getQuantity() * item.getPrice();
                        totalValue += itemTotal;
            %>
            <tr class="item-row">
                <td><input type="checkbox" class="select-item" name="product_id" value="<%= item.getProduct_id() %>" onclick="updateTotal()"></td>
                <td><%= item.getProduct_id() %></td>
                <td><input type="number" name="quantity_<%= item.getProduct_id() %>" value="<%= item.getQuantity() %>" min="1" class="quantity-input" oninput="updateTotal()"></td>
                <td class="price">$<%= item.getPrice() %></td>
                <td class="item-total">$<%= itemTotal %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5">Your cart is empty.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <div class="mb-3">
            <label for="UserId" class="form-label">User ID</label>
            <input type="text" class="form-control" id="UserId" name="userId" value="<%= userId %>" readonly>
        </div>
        <div class="mb-3">
            <label for="orderDate" class="form-label">Order Date</label>
            <input type="date" class="form-control" id="orderDate" name="orderDate" required>
        </div>
        <div class="mb-3">
            <label for="discount" class="form-label">Discount</label>
            <input type="number" class="form-control" id="discount" name="discount" value="0" oninput="updateTotal()">
        </div>
        <div class="mb-3">
            <label for="totalAmount" class="form-label">Total Amount</label>
            <input type="text" class="form-control" id="totalAmount" name="totalAmount" value="<%= totalValue %>" readonly>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Status</label>
            <input type="text" class="form-control" id="status" name="status" value="Pending" readonly>
        </div>
        <div class="mb-3">
            <label for="paymentMethod" class="form-label">Payment Method</label>
            <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                <option value="Credit Card">Credit Card</option>
                <option value="PayPal">PayPal</option>
                <option value="Bank Transfer">Bank Transfer</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Place Order</button>
    </form>
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
                    <li><a href="customerdashboard.jsp" class="text-white">Home</a></li>
                    <li><a href="customerproducts" class="text-white">Browse Products</a></li>
                    <li><a href="shopping-cart.jsp" class="text-white">Shopping Cart</a></li>
                    <li><a href="order-history" class="text-white">Order History</a></li>
                    <li><a href="customer" class="text-white">Profile</a></li>
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
</body>
</html>