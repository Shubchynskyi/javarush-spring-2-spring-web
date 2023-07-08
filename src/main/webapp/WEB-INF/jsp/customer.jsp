<jsp:useBean id="customer" scope="request"
             type="com.example.javarushspring2springweb.entity.Customer"/>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h1>Order data</h1>
<p>Name: ${customer.id}</p>
<p>Name: ${customer.name}</p>
<p>Password: ${customer.password}</p>


</body>
</html>

