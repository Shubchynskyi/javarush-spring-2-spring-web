<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HTML Customers</title>
</head>
<h2>Customers!</h2>
<body>
<div>
    <div>
        <c:forEach var="customer" items="${requestScope.customers}">
            <ul>
                <a href="customer?id=${customer.id}">${customer.name}</a>
            </ul>
        </c:forEach>
    </div>
</div>
<div>
    <form method="POST" action="customers">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Create Customer</button>
    </form>
</div>
</body>
</html>
