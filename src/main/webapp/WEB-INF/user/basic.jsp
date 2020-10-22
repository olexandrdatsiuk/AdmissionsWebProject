<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sql" uri="/WEB-INF/taglib.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<sql:sqlHandler task="getUserStudyAccDetails" attr="user_study_acc_details"/>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<head>
    <title><fmt:message key="title.account"/></title>
    <meta charset="UTF-8">
    <%@include file="/WEB-INF/jspf/styles.jspf" %>
</head>
<body>
<%@include file="/WEB-INF/jspf/user/navbar.jspf" %>
<br>
<h3><fmt:message key="title.account.h"/></h3><br>
<div class="col-md-8">
    <div class="tab-content profile-tab" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <%@include file="/WEB-INF/jspf/account_details.jspf" %>
            <div class="row">
                <div class="col-md-6"><label><fmt:message key="account.label.score"/></label></div>
                <div class="col-md-6"><p>${requestScope.user_study_acc_details.studyAccount.averageScore}</p></div>
            </div>
            <div class="row">
                <div class="col-md-6"><label><fmt:message key="account.label.university"/></label></div>
                <div class="col-md-6"><p>${requestScope.user_study_acc_details.studyAccount.university.name}</p></div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>