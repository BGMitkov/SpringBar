<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Bar Order Form</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bar.css" />" />
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.1.3.min.js" />"></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/renderItemTypeTables.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/renderItems.js"></c:url>'></script>
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
			<h2>Making an order</h2>
		</div>
		<div align="right" id="nav">
			<div class="login_register">
				<form:form action="/SpringBar/view/registerEmployee" method="GET">
					<button type="submit">Register</button>
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

	<div id="content">
		<div id="tables">
			<table id="items_table" class="table">
				<tr>
					<th>Name</th>
					<th>Price</th>
					<th>Type</th>
					<th>Description</th>
				</tr>
			</table>

		</div>
	</div>
</body>
</html>