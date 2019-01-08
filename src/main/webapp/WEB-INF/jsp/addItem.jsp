<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>For for adding items</title>
<link rel="stylesheet" type="text/css"
	href="/SpringBar/resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/SpringBar/resources/css/bar.css" />
<%-- <script type="text/javascript" src="js/jquery-2.1.3.min.js"></script> --%>
</head>
<body>
	<div align="left">
		<h2>
			<a href="index.jsp">${applicationName}</a>
		</h2>
	</div>
	<h2 align="center">
		<b>Add Item</b>
	</h2>
	<div class="logout">
		<form:form action="/Spring/signout" method="GET">
			<button type="submit" class="btn btn-default">Sign out</button>
		</form:form>
		<a href="/SpringBar/signout"> Logout </a>
	</div>

</body>
</html>