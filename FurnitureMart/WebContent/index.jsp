<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Of Furniture</title>
<link rel="stylesheet" type="text/css" href="styles/homepage.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<div class="header">
 
	 <h1 align="center">Home of Furnitures</h1>

</div>

</head>
<body>
<div class="topnav"> 
  <a href="contact.jsp" style="float:right">Contact Us</a>
 
</div>
<script type="text/javascript">
var Msg ='<%=request.getAttribute("Login")%>';
    if (Msg != "null") {
 function alertName(){
 alert("Login Failed");
 } 
 }
 var Msg ='<%=request.getAttribute("contact")%>';
    if (Msg == "contact") {
 function alertName(){
 alert("Submitted successfully");
 } 
 }
   

 </script> 
<div class="row">
  <div class="column">
    <div class="card">
       <h2>Search, Select and Buy</h2>
		<p>"Everyone deserves to have Awesome handmade furnitures"</p>
	    <img src="images/MainPage.jpg" width="100%" height="100%">
		
	</div>
	</div>
  <div class="column">
    <div class="card">	
	  <form action="HomeServlet" method="post">
	  	
		  	<input type="hidden" name="action" value="Login"> 
		    <label><b>User Name</label><br>
		    <input type="text" id="uname" name="username" placeholder="enter user name.." ><br>
		    <label ><b>Password</b> </label><br>
		    <input type="Password" id="Password" name="Password" placeholder="enter Password"  required><br>
			<input type="checkbox" onchange="document.getElementById('Password').type = this.checked ? 'text' : 'password'"> Show password
		
		    <input type="submit"  value="Login" >
		    <p>Not a member yet? <a href="signup.jsp" title="Sign Up">Sign Up</a></p>
      </form>
	  <form action="HomeServlet" method="post">
	  </form>
	 </div>
  </div>

</div>
	
</body>
<script type="text/javascript"> window.onload = alertName; </script>
</html>
