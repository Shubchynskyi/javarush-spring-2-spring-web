<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HTML Customers</title>
</head>
<h2>Products!</h2>
<body>
<div>
    <div>
        <c:forEach var="product" items="${requestScope.products}">
            <ul>
                <a href="product?id=${product.id}">${product.title}</a>
            </ul>
        </c:forEach>
    </div
    <div>
        <form method="POST" action="products">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" required>

            <button type="submit">Create product</button>
        </form>
    </div>
</div>
</body>
</html>
