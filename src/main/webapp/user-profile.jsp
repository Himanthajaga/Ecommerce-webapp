<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.ecomerce.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function fillUserDetails() {
            var users = JSON.parse('<%= new org.json.JSONArray((List) request.getAttribute("users")).toString() %>');
            var selectedUserId = document.getElementById("userSelect").value;
            var user = users.find(u => u.userId == selectedUserId);
            if (user) {
                document.getElementById("userId").value = user.userId;
                document.getElementById("userName").value = user.userName;
                document.getElementById("email").value = user.email;
                document.getElementById("phone").value = user.phone;
            }
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h1>User Profile</h1>
    <form action="updateUserProfile" method="post">
        <div class="mb-3">
            <label for="userSelect" class="form-label">Select User</label>
            <select id="userSelect" class="form-select" onchange="fillUserDetails()">
                <option value="">Select a user</option>
                <%
                    List<UserDTO> users = (List<UserDTO>) request.getAttribute("users");
                    for (UserDTO user : users) {
                %>
                <option value="<%= user.getUserId() %>"><%= user.getUserName() %></option>
                <%
                    }
                %>
            </select>
        </div>
        <div class="mb-3">
            <label for="userName" class="form-label">User Name</label>
            <input type="text" class="form-control" id="userName" name="userName">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" name="phone">
        </div>
        <input type="hidden" id="userId" name="userId">
        <button type="submit" class="btn btn-primary">Update Profile</button>
    </form>
</div>
</body>
</html>