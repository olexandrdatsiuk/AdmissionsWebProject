<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sql" uri="/WEB-INF/taglib.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <%@include file="/WEB-INF/jspf/styles.jspf" %>
    <title><fmt:message key="title.my.subjects"/></title>
</head>
<body>
<%@include file="/WEB-INF/jspf/user/navbar.jspf" %>
<br>
<h3><fmt:message key="title.my.subjects"/></h3><br>

<sql:sqlHandler task="getSubjectsPerUser" attr="subjects_per_user"/>
<div class="col-md-5">
    <div class="tab-content profile-tab" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <c:choose>
                <c:when test="${not empty requestScope.subjects_per_user}">
                    <br>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="table.subject"/></th>
                            <th class="table_context" scope="col"><fmt:message key="table.score"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="s" items="${requestScope.subjects_per_user}">
                        <tr>
                            <td><c:out value="${s.name}"/></td>
                            <td class="table_context"><c:out value="${s.score}"/></td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <fmt:message key="message.info.subjects.add"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>