<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
    <title><fmt:message key="title.login"/></title>
    <meta charset="UTF-8">
    <%@include file="/WEB-INF/jspf/styles.jspf" %>
</head>

<body>
<%@include file="/WEB-INF/jspf/guest/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div style="width: 300px; margin-top: 100px; " class="container">
                <c:if test="${not empty sessionScope.info_message_activity}">
                    <div class="alert alert-primary" role="alert">
                        <fmt:message key="${sessionScope.info_message_activity}"/>
                        <c:remove var="info_message_activity" scope="session"/>
                    </div>
                </c:if>
                <form method="post" action="${pageContext.request.contextPath}/login">
                    <div class="form-group">
                        <label for="inputEmail"><fmt:message key="form.label.email"/></label>
                        <input type="text" name="email" class="form-control" id="inputEmail"
                               placeholder="<fmt:message key="form.label.email"/>"
                               value="${sessionScope.tried_login_user}">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword"><fmt:message key="form.label.pass"/></label>
                        <input type="password" name="pass" class="form-control" id="inputPassword"
                               placeholder="<fmt:message key="form.label.pass"/>">
                    </div>
                    <div style="text-align: center;">
                        <button type="submit" class="btn btn-outline-primary"><fmt:message
                                key="form.login.button.login"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>