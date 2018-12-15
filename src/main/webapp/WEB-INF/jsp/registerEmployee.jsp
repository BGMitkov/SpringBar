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
	<h1>${applicationName}</h1>
	<form:form method="POST" modelAttribute="user"
		action="/SpringBar/registerEmployeeSubmit">
		<div class="form-group">
			<label for="userName">Username:</label> <input type="text"
				class="form-control" name="name" id="userName" value="">
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