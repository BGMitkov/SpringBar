<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Employee Form</title>
<link rel="stylesheet" type="text/css"
	href="/SpringBar/resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/SpringBar/resources/css/bar.css" />
</head>
<body>

	<div id="header">
		<div align="left">
			<div id="applicationName">
				<h2>${applicationName}</h2>
			</div>
			<div class="welcome-greeting">Welcome, ${name}!</div>
		</div>
		<div align="center">
			<h2>Orders</h2>
		</div>
		<div align="right" id="nav">
			<div class="login_register">
				<form:form action="/SpringBar/view/registerEmployee" method="GET">
					<button type="submit">Register</button>
				</form:form>
			</div>
			<div class="item_order">
				<form:form action="/SpringBar/view/order" method="GET">
					<button type="submit">Order</button>
				</form:form>
			</div>
			<div class="item_order">
				<form:form action="/SpringBar/view/addItem" method="GET">
					<button type="submit">Add Item</button>
				</form:form>
			</div>
			<div class="signout">
				<form:form action="/SpringBar/signout" method="GET">
					<button type="submit">Sing out</button>
				</form:form>
			</div>
		</div>
	</div>

	<form:form method="POST" modelAttribute="user"
		action="/SpringBar/registerEmployeeSubmit">
		<div class="form-group">
			<label for="userName">Username:</label>
			<form:input type="text" path="name" class="form-control" name="name"
				id="userName" value="" />
			<form:errors path="name" cssClass="error" />
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" name="password" id="pwd" value="">
		</div>

		<div class="form-group">
			<label for="conf_pwd">Confirm password:</label> <input
				type="password" class="form-control" name="confirm_password"
				id="conf_pwd" value="">
		</div>

		<div class="form-group">
			<label for="email">Email:</label> <input type="email"
				class="form-control" name="email" id="email" value="">
		</div>

		<div class="form-group">
			<label for="birthDate">BirthDate:</label> <input type="date"
				class="form-control" name="birthDate" id="birthDate">
		</div>

		<div class="form-group">
			<label for="role">Role:</label> <select class="form-control"
				name="role" id="role">
				<option value="UNDEFINED">Select Role</option>
				<option value="SERVER">Server</option>
				<option value="BARTENDER">Bartender</option>
				<option value="MANAGER">Manager</option>
			</select>
		</div>

		<button type="submit" class="btn btn-default">Register</button>
	</form:form>
</body>
</html>