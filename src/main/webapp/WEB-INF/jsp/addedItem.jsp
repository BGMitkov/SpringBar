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
            <td>${item.id}</td>
        </tr>
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
            <td>${item.ItemType}</td>
        </tr>
    </table>
</body>

</html>