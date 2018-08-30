<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Of Furniture</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">
<link rel="stylesheet" type="text/css" href="styles/user.css">
<div class="header">
 
	 <h1 align="center">Home of Furnitures</h1>

</div>
</head>
<body>

<%
HttpSession sesion=request.getSession();
sesion.removeAttribute("User");
sesion.invalidate();
response.sendRedirect("index.jsp");
%>
</body>
</html>