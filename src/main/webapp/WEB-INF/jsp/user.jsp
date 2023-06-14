<jsp:useBean id="user" scope="request" type="com.example.javarushspring2springweb.lessons1_7.entity.User"/>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h1>Order data</h1>
<p>Name: ${user.id}</p>
<p>Name: ${user.name}</p>
<p>Password: ${user.password}</p>


</body>
</html>

