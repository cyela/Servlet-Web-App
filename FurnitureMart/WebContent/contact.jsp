<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles/user.css">
<link rel="stylesheet" type="text/css" href="styles/contact.css">
<title>Home Of Furniture</title>
<div class="header">
	<h1 align="center">Home of Furnitures</h1>
</div>
</head>
<body background="images/bg1.jpg">
	<div class="topnav">
		<a href="HomeServlet?action=home">Home</a>
	</div>
	<h3>Contact Us</h3>
	<div class="container">
		<form action="HomeServlet" method="get">
			<input type="hidden" name="action" value="contact"> <label
				for="fname">Name</label> <input type="text" id="fname"
				name="firstname" placeholder="Your name.."> <label
				for="lname">Email</label> <input type="email" id="email"
				name="email" placeholder="Your email.."> <label
				for="subject">Subject</label>
			<textarea id="subject" name="subject" placeholder="Write something.."
				style="height: 200px"></textarea>
			<input type="submit" value="send mail">
		</form>
	</div>
</body>
</html>