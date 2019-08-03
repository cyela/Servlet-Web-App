<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="styles/signup.css">
<div class="header">
	<h1 align="center">Home of Furnitures</h1>
</div>
</head>
<body background="images/bg1.jpg">
	<script type="text/javascript">
		function checkForm(form) {

			if (form.Password.value != ""
					&& form.Password.value == form.rePassword.value) {
				if (form.Password.value.length < 6) {
					alert("Error: Password must contain at least six characters!");
					form.pwd1.focus();
					return false;
				}

				re = /[0-9]/;
				if (!re.test(form.Password.value)) {
					alert("Error: password must contain at least one number (0-9)!");
					form.pwd1.focus();
					return false;
				}
				re = /[a-z]/;
				if (!re.test(form.Password.value)) {
					alert("Error: password must contain at least one lowercase letter (a-z)!");
					form.pwd1.focus();
					return false;
				}
				re = /[A-Z]/;
				if (!re.test(form.Password.value)) {
					alert("Error: password must contain at least one uppercase letter (A-Z)!");
					form.Password.focus();
					return false;
				}
				re = /[$@$!%*#?&]/;
				if (!re.test(form.Password.value)) {
					alert("Error: password must contain at least one uppercase letter ($@$!%*#?&)!");
					form.Password.focus();
					return false;
				}
			} else {
				alert("Error: Mismatch in password!");
				form.Password.focus();
				return false;
			}
			alert("You entered a valid password: " + form.Password.value);
			return true;
		}
	</script>
	<div class="topnav">
		<a href="index.jsp">Login</a> <a href="contact.jsp"
			style="float: right">Contact Us</a>
	</div>
	<p>Create your account:</p>
	<form action="RegistrationServlet" method="post"
		onsubmit="return checkForm(this);">
		<input type="hidden" name="action" value=sign>
		<div class="column">
			<input type="text" id="fname" name="firstname"
				placeholder="Firstname"> <input type="text" id="lname"
				name="lastname" placeholder="Lastname"><br> <input
				type="radio" name="gender" value="male" checked> Male <input
				type="radio" name="gender" value="female"> Female <input
				type="date" name="bday"><br> <input type="email"
				id="email" name="email" placeholder="Email"><br> <input
				type="number" id="phonenumber" name="phonenumber"
				placeholder="Phonenumber"><br> <input type="text"
				id="Password" name="Password" placeholder="Password"
				onkeyup="CheckPasswordStrength(this.value)" required /> <span
				id="password_strength"></span>
			<script type="text/javascript">
				function CheckPasswordStrength(password) {
					var password_strength = document
							.getElementById("password_strength");

					//TextBox left blank.
					if (password.length == 0) {
						password_strength.innerHTML = "";
						return;
					}

					//Regular Expressions.
					var regex = new Array();
					regex.push("[A-Z]"); //Uppercase Alphabet.
					regex.push("[a-z]"); //Lowercase Alphabet.
					regex.push("[0-9]"); //Digit.
					regex.push("[$@$!%*#?&]"); //Special Character.

					var passed = 0;

					//Validate for each Regular Expression.
					for (var i = 0; i < regex.length; i++) {
						if (new RegExp(regex[i]).test(password)) {
							passed++;
						}
					}

					//Validate for length of Password.
					if (passed > 2 && password.length > 6) {
						passed++;
					}

					//Display status.
					var color = "";
					var strength = "";
					switch (passed) {
					case 0:
					case 1:
						strength = "Weak";
						color = "red";
						break;
					case 2:
						strength = "Good";
						color = "darkorange";
						break;
					case 3:
					case 4:
						strength = "Strong";
						color = "green";
						break;
					case 5:
						strength = "Very Strong";
						color = "darkgreen";
						break;
					}
					password_strength.innerHTML = strength;
					password_strength.style.color = color;
				}
			</script>
			<input type="Password" id="rePassword" name="rePassword"
				placeholder="Re-enter Password" required><br> <input
				type="submit" value="Create">
	</form>
</body>
</html>