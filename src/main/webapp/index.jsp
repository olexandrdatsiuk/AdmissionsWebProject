<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>
<%!
    String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return sdf.format(new Date());
    }
%>

<html lang="${sessionScope.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AdmissionsWebProject</title>
</head>
<body>
<h2>
    <fmt:message key="greet"/>, AdmissionsWebProject! <br/>
    <i>Today <%= getFormattedDate() %>
    </i>
</h2>

<br/>
<a href="${pageContext.request.contextPath}/login.jsp">
    <fmt:message key="label.sign.in"/></a> <fmt:message key="label.or"/>
<a href="${pageContext.request.contextPath}/signup.jsp"><fmt:message key="label.sign.up"/></a>

<br>
<a href="${pageContext.request.requestURI}?lang=ua"><fmt:message key="label.button.ua"/> <fmt:message key="label.language"/></a>
<br>
<a href="${pageContext.request.requestURI}?lang=en"><fmt:message key="label.button.en"/> <fmt:message key="label.language"/></a>
</body>
</html>
