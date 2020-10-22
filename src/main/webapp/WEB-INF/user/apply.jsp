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
    <title><fmt:message key="title.apply"/></title>
</head>
<body>

<%@include file="/WEB-INF/jspf/user/navbar.jspf" %>

<sql:sqlHandler task="getFacultiesPerUserByUniversity" attr="faculties_per_user"/>

<br>
<h3><fmt:message key="title.apply"/></h3><br>
<c:if test="${not empty sessionScope.info_message_activity}">
    <div class="row">
        <div class="col-md-12 text-center alert alert-primary" id="message" role="alert">
            <fmt:message key="${sessionScope.info_message_activity}"/>
            <c:remove var="info_message_activity" scope="session"/>
        </div>
    </div>
</c:if>
<div class="col-md-8">
    <div class="tab-content profile-tab" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <c:choose>
                <c:when test="${not empty requestScope.faculties_per_user}">
                    <fmt:message key="sorting.h"/>:
                    <c:forEach var="fc" items="${applicationScope.faculty_comparator}">
                        <a href="${pageContext.request.contextPath}/user/apply.jsp?sort=<c:out value="${fc}"/> "><fmt:message
                                key="${fc.key}"/></a>
                    </c:forEach>
                    <br>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="table.faculty"/></th>
                            <th class="table_context" scope="col"><fmt:message key="table.budget.amount"/></th>
                            <th class="table_context" scope="col"><fmt:message key="table.all.amount"/></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="f" items="${requestScope.faculties_per_user}">
                        <tr>
                            <td><c:out value="${f.name}"/></td>
                            <td class="table_context"><c:out value="${f.freeAmount}"/></td>
                            <td class="table_context"><c:out value="${f.allAmount}"/></td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/user/apply">
                                    <input type="hidden" name="faculty" value="<c:out value="${f.id}"/>">
                                    <input type="submit" class="btn btn-outline-primary btn-sm"
                                           value="<fmt:message key="form.apply.button.apply"/>">
                                </form>
                            </td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <fmt:message key="message.info.faculties.nothing"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>