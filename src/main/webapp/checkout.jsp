<%@ page import="lk.ijse.ecomerce.dto.CartDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
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
                    <a class="nav-link" href="order-history.jsp">Order History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="user-profile.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2>Checkout</h2>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>