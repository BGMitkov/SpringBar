<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@page session="true"%>

<!DOCTYPE html>
<html>
4
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Bar Index</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bar.css" />" />
<script type="text/javascript" src="/resources/js/jquery-2.1.3.min.js"></script>
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

	<div id="content">
		<div class="table_with_orders_div" id="leftTable">
			<div class="table_header">
				<h2>Waiting</h2>
			</div>
			<div>
				<table id="waiting_orders_table" class="table">
					<tr>
						<th>Id</th>
						<th>Items</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</table>
			</div>
		</div>
		<div class="table_with_orders_div" id="rightTable">
			<div class="table_header">
				<h2>Accepted</h2>
			</div>
			<div>
				<table id="current_orders_table" class="table">
					<tr>
						<th>Id</th>
						<th>Items</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<!-- 	<hr> -->
</body>
</html>
