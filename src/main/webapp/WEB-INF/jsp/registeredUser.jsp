<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>

<body>
    <h2>Registered User</h2>

    <table>
        <tr>
            <td>id</td>
            <td>${user.id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${user.username}</td>
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