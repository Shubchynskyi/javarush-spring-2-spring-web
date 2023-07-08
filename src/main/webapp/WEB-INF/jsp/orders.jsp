<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HTML Customers</title>
</head>
<h2>Orders!</h2>
<body>
<div>
    <div>
        <c:forEach var="order" items="${requestScope.orders}">
            <li>
            <li>Order customer - ${order.customer.name}</li>
            <li>Order address - ${order.address}</li>
            <li>
                <a href="order?id=${order.id}">Order description</a>
            </li>

        </c:forEach>
    </div>
</div>
<div>
    <h1>Create Order</h1>
    <form method="post" action="orders">
        <label for="customer">Customer:</label>
        <select id="customer" name="customerId">
            <c:forEach items="${requestScope.customers}" var="customer">
                <option value="${customer.id}">${customer.name}</option>
            </c:forEach>
        </select>
        <br><br>
        <label for="address">Delivery Address:</label>
        <input type="text" id="address" name="address">
        <br><br>
        <c:forEach items="${requestScope.products}" var="product">
            <input type="checkbox" id="product-${product.id}" name="productIds" value="${product.id}">
            <label for="product-${product.id}">${product.title}</label>
            <br>
        </c:forEach>
        <br>
        <input type="submit" value="Create Order">
    </form>
</div>
</body>
</html>
