<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<!-- <link rel="stylesheet" type="text/css" href="/SpringBar/resources/css/bootstrap.css" /> -->
<!-- <link rel="stylesheet" type="text/css" href="/SpringBar/resources/css/bar.css" /> -->
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/css/bar.css" var="barCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${barCss}" rel="stylesheet" />
</head>
<body>
	<div align="left">
		<h2>
			<a href="index.html">${applicationName}</a>
		</h2>
	</div>
	<hr>
	<h1 align="center">
		<b>Log in</b>
	</h1>

	<div class="login_register">
		<a href="addingOrder.html" class="add_order_form">Add Order</a> <a
			href="addItem.html" class="add_item_form">Add Items</a> <a
			href="register.html" class="register_form"> Register </a>
	</div>

	<form:form id="login_form" method="post"
		action="/SpringBar/loginSubmit">
		<div class="form-group">
			<label for="userName">Username:</label> <input type="text"
				class="form-control" name="name" id="name" value="">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" name="password" id="password" value="">
		</div>

		<button type="reset" class="btn btn-default" id="reset_login">Reset</button>
		<button type="submit" class="btn btn-default">Log in</button>
	</form:form>
</body>
</html>