<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registered Employee Data</title>
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/css/bar.css" var="barCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${barCss}" rel="stylesheet" />
</head>

<body>
    <h1>${applicationName}</h1>
    <table>
        <tr>
            <td>id</td>
            <td>${user.id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${user.name}</td>
        </tr>
        <tr>
            <td>email</td>
            <td>${user.email}</td>
        </tr>
        <tr>
            <td>role</td>
            <td>${user.role}</td>
        </tr>
        <tr>
            <td>birthDate</td>
            <td>${user.birthDate}</td>
        </tr>
    </table>
</body>

</html>