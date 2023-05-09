<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HTML Users</title>
</head>
<h2>Users!</h2>
<body>
<div>
    <div>
        <c:forEach var="user" items="${requestScope.users}">
            <ul>
                <a href="user?id=${user.id}">${user.name}</a>
            </ul>
        </c:forEach>
    </div>
</div>
</body>
</html>
