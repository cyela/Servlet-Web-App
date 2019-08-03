<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.business.User"%>
<%@ page import="com.business.Product"%>
<%@ page import="com.business.Address"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Of Furniture</title>
<link rel="stylesheet" type="text/css" href="styles/user.css">
<link rel="stylesheet" type="text/css" href="styles/product.css">
<div class="header">
	<h1 align="center">Home of Furnitures</h1>
</div>
</head>
<body background="images/bg1.jpg">
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.
		if (session.getAttribute("User") == null) {
			response.sendRedirect("index.jsp");
		} else {
			User user = (User) request.getSession().getAttribute("User");
			ArrayList<Product> product = (ArrayList) request.getSession().getAttribute("product");
			Address adrs = (Address) request.getSession().getAttribute("address");

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
 var Msg1='<%=request.getAttribute("cart_item")%>';

	if (Msg1 == "added")
	{
		 function alertName(){
		 alert("Item has been successfully added to cart");
		 }
	} else if (Msg1 == "exists")
	{
		 function alertName(){
		 alert("Already in cart\nYou can change quantity by visiting View Cart Page\nThank you");
		 }
	}    
	var Msg ='<%=request.getAttribute("cart")%>';
    if (Msg == "placed")
    {
			 function alertName(){
				 
			 alert("Your order has been placed successfully");
			 }
			 
    } else if (Msg == "empty")
	{
		 function alertName(){
		 alert("Sorry your cart is empty");
		 }
	} 
    
 var Msg ='<%=request.getAttribute("contact")%>';
    if (Msg == "contact") {
		 function alertName(){
	 	alert("Thank you for contacting us");
	 } 
 }
    
    var Msg ='<%=request.getAttribute("address")%>
		';
		if (Msg == "address") {
			function alertName() {
				alert("Address updated successfully");
			}
		}
	</script>
	<div class="topnav">
		<a href="HomeServlet?action=home">Home</a> <a href="logout.jsp"
			style="float: right">Log out</a> <a
			href="CartServlet?action=view_cart" style="float: right">View
			Cart</a>
	</div>
	<div class="row">
		<div class="leftcolumn">
			<div class="prodrow">
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<c:forEach var="item" items="${product}">
					<div class="prodcolumn">
						<img class="thumbnail zoom"
							src="${pageContext.servletContext.contextPath }/ImageServlet?productId=<c:out value='${item.getProductId()}'/>"
							alt="" style="align: left; width: 200px; height: 200px" />

						<p align="center">Product Name: ${item.getProductname()}</p>
						<p align="center">Price: ${item.getProductprice()}$ only</p>
						<form action="CartServlet" method="get">
							<input type="hidden" name="action" value="cart_item"> <input
								type="hidden" name="productId" value="${item.getProductId()}">
							<input type="submit" value="add to cart">
						</form>
					</div>
				</c:forEach>
			</div>
		</div>

		<div class="rightcolumn">
			<div class="row">
				<fieldset>
					<legend>Profile</legend>
					<div class="innerleftcolumn">
						<form action="ProductServlet" method="post"
							enctype="multipart/form-data">
							<%
								User usernn = (User) request.getSession().getAttribute("User");
							%>
							<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
							<%
								if (usernn.getImage() != null) {
							%>
							<img
								src="${pageContext.servletContext.contextPath }/ImageServlet?email=${user.email}"
								alt="" style="width: 75%">

							<%
								} else {
							%>
							<img src="images/noimage.png" alt="" style="width: 75%">
							<%
								}
							%>
							<input type="button" id="myBtnpic" value="Edit Picture"
								style="width: 75%">
						</form>
					</div>
					<div class="innerrightcolumn">
						<h3>${user.firstName},${user.lastName}</h3>
						<p>
							Address <input type="button" id="myBtn" value="Update">
						</p>
						<p>${address.address}</p>
						<p>${address.city}</p>
						<p>${address.state}</p>
						<p>${address.zipcode}</p>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<!-- The Modal Address change -->
	<div id="myModal" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">&times;</span>
				<h2>Address</h2>
			</div>
			<div class="modal-body">
				<div>
					<form action="UserServlet" method="post">
						<input type="hidden" name="action" value="addresschange">
						<input type="hidden" name="email" value="${user.email}">
						<input type="email" name="email" value="${user.email}"><br>
						<input type="text" name="address" value="${address.address}"
							placeholder="Street, Apartment , unit, building"><br>
						<input type="text" name="city" value="${address.city}"
							placeholder="City"><br> <input type="text"
							name="state" value="${address.state}" placeholder="State"><br>
						<input type="text" name="zipcode" value="${address.zipcode}"
							placeholder="zipcode"><br> <input type="submit"
							value="Update">
					</form>
				</div>
			</div>
		</div>

	</div>
	<script>
		// Get the modal
		var modal = document.getElementById('myModal');

		// Get the button that opens the modal
		var btn = document.getElementById("myBtn");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks the button, open the modal 
		btn.onclick = function() {
			modal.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>

	<!-- The Modal Profile Picture change -->
	<div id="myModalpic" class="modal">
		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">&times;</span>
				<h2>Profile Picture</h2>
			</div>
			<div class="modal-body">
				<div>
					<form action="UserServlet" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="action" value="picturechange">
						<div>
							<p>
								<input type="file" name="photo" size="50" id="uploadfile"
									value="Upload" /> <input type="submit" value="Upload">
							</p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		// Get the modal
		var modal1 = document.getElementById('myModalpic');

		// Get the button that opens the modal
		var btn1 = document.getElementById("myBtnpic");

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[1];

		// When the user clicks the button, open the modal 
		btn1.onclick = function() {
			modal1.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal1.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal1) {
				modal1.style.display = "none";
			}
		}
	</script>
</body>
<script type="text/javascript">
	window.onload = alertName;
</script>
</html>