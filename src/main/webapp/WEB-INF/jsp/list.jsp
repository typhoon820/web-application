<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Users</title>
</head>
<body>
<h2>  List of Users  </h2>
<table>
    <tr>
        <td>Login</td>

    </tr>
    <tr>
        <c:forEach var = "user" items = "${users}">
            <td>${user}</td>
        </c:forEach>

    </tr>
</table>

</body>
</html>