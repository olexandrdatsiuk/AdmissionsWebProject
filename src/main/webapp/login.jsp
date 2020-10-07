<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login in system</title>
</head>
<body>

<h1>Login in system</h1><br/>
<form method="post" action="${pageContext.request.contextPath}/login">

    <input type="text" name="name"><br/>
    <input type="password" name="pass"><br/><br/>
    <input class="button" type="submit" value="Log in">

</form>
<br/>
<a href="${pageContext.request.contextPath}/index.jsp">На головну</a>
</body>
</html>