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
<div>
    <form method="POST" action="users">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Create User</button>
    </form>
</div>
</body>
</html>
