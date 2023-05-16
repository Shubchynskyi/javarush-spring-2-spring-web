<jsp:useBean id="product" scope="request" type="com.example.javarushspring2springweb.entity.Product"/>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h1>Product data</h1>
<p>Id: ${product.id}</p>
<p>Title: ${product.title}</p>
<p>Description: ${product.description}</p>
</body>
</html>


