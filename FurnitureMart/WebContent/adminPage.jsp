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
<link rel="stylesheet" type="text/css" href="styles/admin.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<div class="header">
 
	 <h1 align="center">Home of Furnitures</h1>

</div>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.
if(session.getAttribute("User")==null){
	response.sendRedirect("index.jsp");
}else{
	User user = (User) request.getSession().getAttribute("User");
	ArrayList<Product> product=(ArrayList) request.getSession().getAttribute("product");
	
	
}
%>
<script type="text/javascript">
var Msg ='<%=request.getAttribute("Login")%>';
    if (Msg == "success")
    {
			 function alertName(){
			 alert("Logged in successfully");
			 }
			 
    } 
var Msg1='<%=request.getAttribute("function")%>';

	if (Msg1 == "add")
	{
		 function alertName(){
		 alert("Added successfully");
		 }
	} else if(Msg1 == "edit"){
		function alertName(){
			 alert("Updated successfully");
			 }
	}else if(Msg1 == "remove")
	{
		function alertName(){
			 alert("removed successfully");
			 }
	}

 </script> 
 <div class="topnav"> 
		<a href="logout.jsp" style="float:right">Log out</a>
  </div> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="ProductServlet" method="post" enctype="multipart/form-data">
<div class="container">
	<div  class="row">
	        <div class=" col-xs-12 col-md-6">
	        	<h2>Welcome ${user.firstName}, ${user.lastName}</h2>
				<h4>To add a product</h4>
	       	</div>
	 </div>

	<div  class="row">
	
	        <div class=" col-sm-2 col-md-24">
	        <input type="text" name="desc" placeholder="Description" >
	        </div>
	          <div class=" col-sm-2 col-md-24">
			<input type="text" name="name" placeholder="Name">
			</div>
			  <div class=" col-sm-2 col-md-24">
			<input type="number" name="price" placeholder="Price">
			</div>
			  <div class=" col-sm-2 col-md-24">
			<input type="number" name="quantity" placeholder="Quantity" >
			</div>
			  <div class=" col-sm-2 col-md-24">
			<input type="file" name="photo" size="50" placeholder="Upload Your Image"  >
			</div>
			  <div class=" col-sm-2 col-md-24">
			  <input type="hidden" name="action" value=add>
			 <input type="submit" value="ADD" >
			 </div>
	     </div>   
	
</div>
</form>
<form action="ProductServlet" method="post">
	<div class="container">
	<div  class="row">
	        <div class=" col-xs-12 col-md-6">
	        	<h4>Added Products</h4>
	       	</div>
		 </div>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:forEach var="item" items="${product}">
		
	    <div  class="row">
	        <div class=" col-s-12 col-md-6">
	            <div class="thumbnail">
	            	<div class="col-xs-12 col-md-6" style="align:center">
	                      
	                    <div class="caption" style="align:right">
	                    <h4 class="list-group-item-heading">
	                        <c:out value='${item.getProductname()}'/></h4>
	                    <p class="list-group-item-text">
	                        <c:out value='${item.getDescription()}'/></p>
	                    <div class="row">
	                        <div class="col-xs-12 col-md-6">
	                            <p class="lead">
	                               <c:out value='${item.getProductprice()}'/> $</p>
	                        </div>
	                        
	                    </div>
	                    
	                </div>
	                 </div>
	               		 <img  src="${pageContext.servletContext.contextPath }/ImageServlet?productId=<c:out value='${item.getProductId()}'/>" alt="" 
	                			style="align:left;width:200px; height:200px"/>
	                	<form action="ProductServlet" method="post">
				    		<input type="hidden" name="action" value="edit">
					        <input type="hidden" name="productId" value="<c:out value='${item.getProductId()}'/>">
				    		<input type="submit" value="EDIT" style="width:25%" >
				   		 </form>
		    
					    <form action="ProductServlet" method="post">
				    		<input type="hidden" name="action" value="remove">
					        <input type="hidden" name="productId" value="<c:out value='${item.getProductId()}'/>">
				    		<input type="submit" value="DELETE" style="width:25%" >
					    </form>
	                
	            </div>
	           
	        </div>
	        </div>
        </c:forEach>
        </div>
</form>

</body>
<script type="text/javascript"> window.onload = alertName; </script>
</html>
