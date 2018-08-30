<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.business.User" %>
<%@ page import="com.business.Product" %>
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
<body background="images/bg1.jpg">
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.
if(session.getAttribute("User")==null){
	response.sendRedirect("index.jsp");
}else{
	User user = (User) request.getAttribute("User");
	Product product=(Product) request.getAttribute("editprod");
	
	
}
%>
<div class="topnav"> 
		<a href="logout.jsp" style="float:right">Log out</a>
  </div> 

<div>
		  <form action="ProductServlet" method="post">
		  <input type="hidden" name="action" value=edit_item>
		  	<fieldset>
		  	  <input type="text" name="productId"  value='${editprod.getProductId()}' readonly>
			  <input type="text" name="desc" value='${editprod.getDescription()}'>
			  <input type="text" name="name" value='${editprod.getProductname()}'>
			  <input type="text" name="price"  value='${editprod.getProductprice()}'>
			  <input type="text" name="quantity" value='${editprod.getQuantity()}'>
		    <input type="submit" value="Update" >
		  </form>
		  <form action="ProductServlet" method="post">
					  <input type="hidden" name="action" value=return>
				      <input type="submit" name="cancel" value="cancel">
		   	 </form>
		   	  </div>
		</fieldset>
</div>
</body>
</html>