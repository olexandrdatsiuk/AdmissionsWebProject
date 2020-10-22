<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="/WEB-INF/taglib.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>
<sql:sqlHandler task="getAllUniversities" attr="university_names"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.signup"/></title>
    <%@include file="/WEB-INF/jspf/styles.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/guest/navbar.jspf" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div style="width: 700px; margin-top: 30px; " class="container">
                <c:if test="${not empty sessionScope.info_message_activity}">
                    <div class="alert alert-primary" role="alert" style="text-align: center;">
                        <fmt:message key="${sessionScope.info_message_activity}"/>
                        <c:remove var="info_message_activity" scope="session"/>
                    </div>
                </c:if>
                <form method="post" action="${pageContext.request.contextPath}/signup">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputEmail"><fmt:message key="form.label.email"/></label>
                            <input type="text" name="email" class="form-control" id="inputEmail"
                                   placeholder="<fmt:message key="form.label.email"/>"
                                   value="${sessionScope.tried_signup_user_data.email}"><br/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputPassword"><fmt:message key="form.label.pass"/></label>
                            <input type="password" name="pass" class="form-control" id="inputPassword"
                                   placeholder="<fmt:message key="form.label.pass"/>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="inputFirstName"><fmt:message key="form.signup.label.firstname"/></label>
                            <input type="text" name="first_name" class="form-control" id="inputFirstName"
                                   placeholder="<fmt:message key="form.signup.label.firstname"/>"
                                   value="${sessionScope.tried_signup_user_data.account.firstName}">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputLastName"><fmt:message key="form.signup.label.lastname"/></label>
                            <input type="text" name="last_name" class="form-control" id="inputLastName"
                                   placeholder="<fmt:message key="form.signup.label.lastname"/>"
                                   value="${sessionScope.tried_signup_user_data.account.lastName}">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputMiddleName"><fmt:message key="form.signup.label.middlename"/></label>
                            <input type="text" name="middle_name" class="form-control" id="inputMiddleName"
                                   placeholder="<fmt:message key="form.signup.label.middlename"/>"
                                   value="${sessionScope.tried_signup_user_data.account.middleName}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputAddress"><fmt:message key="form.signup.label.city"/></label>
                            <input type="text" name="city" class="form-control" id="inputAddress"
                                   placeholder="<fmt:message key="form.signup.label.city"/>"
                                   value="${sessionScope.tried_signup_user_data.account.city}">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputAddress2"><fmt:message key="form.signup.label.region"/></label>
                            <input type="text" name="region" class="form-control" id="inputAddress2"
                                   placeholder="<fmt:message key="form.signup.label.region"/>"
                                   value="${sessionScope.tried_signup_user_data.account.region}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-2">
                            <label for="inputScore"><fmt:message key="form.signup.label.score"/></label>
                            <input type="number" step="0.1" name="average_score" class="form-control" id="inputScore"
                                   placeholder="<fmt:message key="form.signup.label.score"/>"
                                   value="${sessionScope.tried_signup_user_data.studyAccount.averageScore}">
                        </div>
                        <div class="form-group col-md-10">
                            <label for="inputUniversity"><fmt:message key="form.label.university"/></label>
                            <select name="university" id="inputUniversity" class="form-control">
                                <c:forEach var="u" items="${requestScope.university_names}">
                                    <option value="<c:out value="${u.id}"/>"
                                            <c:if test="${u.id == sessionScope.tried_signup_user_data.studyAccount.university.id}">selected</c:if>
                                    ><c:out value="${u.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div style="text-align: center;">
                        <button type="submit" class="btn btn-outline-primary"><fmt:message
                                key="form.login.button.signup"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<c:remove var="tried_signup_user_data" scope="session"/>
</body>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</html>