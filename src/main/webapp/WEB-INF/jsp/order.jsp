<jsp:useBean id="order" scope="request" type="com.example.javarushspring2springweb.lessons1_7.entity.Order"/>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HTML Users</title>
</head>
<h2>Order data</h2>
<body>
<ul>
    <li>${order.id}</li>
    <li>${order.user.name}</li>
    <li>${order.address}</li>
    <c:forEach var="pos" items="${order.orderList}">
        <li>
           ${pos}
        </li>
    </c:forEach>
</ul>
</body>
</html>
