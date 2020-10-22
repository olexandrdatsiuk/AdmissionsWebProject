<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.welcome"/></title>
    <%@include file="/WEB-INF/jspf/styles.jspf" %>
</head>

<body>
<%@include file="/WEB-INF/jspf/guest/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12" id="welcome">
            <div class="d-flex justify-content-center" style="padding: 100px;">
                <div style="background-color: white; opacity: 0.8; text-align: left; width: 500px;">
                    <fmt:message key="message.welcome.part.user"/><br>
                    <fmt:message key="message.welcome.part.admin"/><br>
                    <fmt:message key="message.welcome.part.general"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</html>