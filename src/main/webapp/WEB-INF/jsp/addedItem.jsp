<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Bar Added Item Information</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bar.css" />" />
<!-- <script type="text/javascript" src="/resources/js/jquery-2.1.3.min.js"></script> -->
<!-- <script type="text/javascript" src="/resources/js/orderTableFiller.js"></script> -->
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
			<h2>Added Item Information</h2>
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
				<form:form action="/SpringBar/view/addItemForm" method="GET">
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

	<table>
		<tr>
			<td>Name</td>
			<td>${item.name}</td>
		</tr>
		<tr>
			<td>description</td>
			<td>${item.description}</td>
		</tr>
		<tr>
			<td>price</td>
			<td>${item.price}</td>
		</tr>
		<tr>
			<td>type</td>
			<td>${item.itemType}</td>
		</tr>
	</table>
</body>

</html>