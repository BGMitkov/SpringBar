<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>

<body>
    <h2>Submitted Student Information</h2>

    <table>
        <tr>
            <td>id</td>
            <td>${id}</td>
        </tr>
        <tr>
            <td>Name</td>
            <td>${name}</td>
        </tr>
        <tr>
            <td>description</td>
            <td>${description}</td>
        </tr>
        <tr>
            <td>price</td>
            <td>${price}</td>
        </tr>
        <tr>
            <td>type</td>
            <td>${type}</td>
        </tr>
    </table>
</body>

</html>