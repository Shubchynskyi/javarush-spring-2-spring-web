<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HTML Users</title>
</head>
<h2>Orders!</h2>
<body>
<div>
    <div>
        <c:forEach var="order" items="${requestScope.orders}">
            <li>
            <tr>Order user - ${order.user.name}</tr>
            <tr>Order address - ${order.address}</tr>
            <tr>
                <a href="order?id=${order.id}">Order description</a>
            </tr>
            </li>
        </c:forEach>
    </div>
</div>
</body>
</html>
