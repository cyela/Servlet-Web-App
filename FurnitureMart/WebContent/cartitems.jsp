<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.business.User"%>
<%@ page import="com.business.Product"%>
<%@ page import="com.dao.CartDAO"%>
<%@ page import="com.business.BufCart"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Of Furniture</title>
<link rel="stylesheet" type="text/css" href="styles/user.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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

			User user = (User) session.getAttribute("User");

		}
	%>
	<div class="topnav">
		<a href="HomeServlet?action=home">Home</a> <a href="logout.jsp"
			style="float: right">Log out</a>
	</div>
	<div class="row1">
		<div class="column1">
			<div style="float: left; width: 750px;">
				<p>Your Cart items</p>
				<table id="product" style="width: 750px;">
					<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
					<c:forEach var="item" items="${bufitems}">
						<tr>
							<td><form action="CartServlet" method="get">
									<input type="hidden" name="action" value="cart_remove">
									<input type="hidden" name="productId"
										value="<c:out value='${item.getProductId()}'/>">
									<p>
										<input type="submit" value="-"
											style="width: 30px; background: red; height: 30px;">
										<c:out value='${item.getProductName()}' />
									</p>
								</form></td>
							<td><c:out value='${item.getPrice()*item.getQuantity()}' />$</td>
							<td>
								<form action="CartServlet" method="get">
									<input type="text" name="quantity"
										value='${item.getQuantity()}' style="width: 80px;"
										style="text-align:center;"> <input type="hidden"
										name="action" value="cart_update"> <input
										type="hidden" name="productId"
										value="<c:out value='${item.getProductId()}'/>"> <input
										type="submit" value="+" style="width: 50px;">
								</form>
							</td>
							<td></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="column1">
			<div style="float: left; width: 600px;">
				<table id="product">
					<tr>
						<td>
							<p>Please click the button to continue shopping</p>
							<form action="HomeServlet" method="get">
								<input type="hidden" name="action" value="home"> <input
									type="submit" value="Continue shopping" style="width: 180px;">
							</form>
							<p>Total value of the order:${Tot} $ only</p>
							<p>Please click the button to place your order</p>
							<form action="CartServlet" method="get">
								<input type="hidden" name="action" value="place"> <input
									type="submit" value="Place Order" style="width: 150px;">
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>