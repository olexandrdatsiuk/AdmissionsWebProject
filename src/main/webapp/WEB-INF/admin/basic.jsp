<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<h1>Admin basic</h1>
<form action="${pageContext.request.contextPath}/logout">
    <input class="button" type="submit" value="Log out">
</form>
</body>
</html>