<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Bar Add Item Form</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bar.css" />" />
<%-- <script type="text/javascript" src="/resources/js/jquery-2.1.3.min.js"></script> --%>
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
			<h2>Add Item</h2>
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
			<div class="signout">
				<form:form action="/SpringBar/signout" method="GET">
					<button type="submit">Sing out</button>
				</form:form>
			</div>
		</div>
	</div>

	<form:form method="POST" modelAttribute="item"
		action="/SpringBar/addItem">
		<div class="form-group">
			<label for="name">ItemName:</label>
			<form:input type="text" path="name" class="form-control" name="name"
				id="name" />
			<form:errors path="name" cssClass="error" />
		</div>

		<div class="form-group">
			<label for="price">Price in coins:</label>
			<form:input type="number" min="1" path="price" class="form-control"
				name="price" id="price" />
			<form:errors path="price" cssClass="error" />
		</div>

		<div class="form-group">
			<label for="itemType">Item Type:</label>
			<form:input type="text" path="itemType" class="form-control"
				name="itemType" id="itemType" />
			<form:errors path="itemType" cssClass="error" />
		</div>

		<div class="form-group">
			<label for="description">Description:</label>
			<form:input type="text" path="description" class="form-control"
				name="description" id="description" />
			<form:errors path="description" cssClass="error" />
		</div>

		<button type="submit" class="btn btn-default">Submit</button>
	</form:form>
</body>
</html>