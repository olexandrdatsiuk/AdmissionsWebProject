<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sql" uri="/WEB-INF/taglib.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.training.controller.FieldConst" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="content"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <%@include file="/WEB-INF/jspf/styles.jspf" %>
    <title><fmt:message key="title.applies"/></title>
</head>
<body>
<%@include file="/WEB-INF/jspf/admin/navbar.jspf" %>
<br>
<sql:sqlHandler task="getAllRequestsWithPagination" attr="requests_of_users"/>
<h3><fmt:message key="title.applies.h"/></h3><br>

<c:if test="${not empty sessionScope.info_message_activity}">
    <div class="row">
        <div class="col-md-12 text-center alert alert-primary" id="message" role="alert">
            <fmt:message key="${sessionScope.info_message_activity}"/>
            <c:remove var="info_message_activity" scope="session"/>
        </div>
    </div>
</c:if>
<div class="col-md-12">
    <div class="tab-content profile-tab" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <c:choose>
                <c:when test="${not empty requestScope.requests_of_users}">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6" style="<%--background-color: red;--%> font-size: 20px;">
                                <c:if test="${param.start_from != null && param.start_from > 0}">
                                    <a href="${pageContext.request.contextPath}/admin/requests.jsp?start_from=${sessionScope.last_start_from - Const.MAX_REQUESTS_ON_PAGE}"><fmt:message
                                            key="pagination.button.back"/></a>
                                </c:if>
                            </div>
                            <div class="col-md-6"
                                 style="font-size: 20px; text-align: right">
                                <c:if test="${not empty requestScope.button_next}">
                                    <a href="${pageContext.request.contextPath}/admin/requests.jsp?start_from=${sessionScope.last_start_from + Const.MAX_REQUESTS_ON_PAGE}"><fmt:message
                                            key="pagination.button.next"/></a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <br>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="table.login"/></th>
                            <th scope="col"><fmt:message key="table.university"/></th>
                            <th scope="col"><fmt:message key="table.faculty"/></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="r" items="${requestScope.requests_of_users}">
                            <tr>
                                <td><c:out value="${r.user.email}"/></td>
                                <td><c:out value="${r.university.name}"/></td>
                                <td><c:out value="${r.faculty.name}"/></td>
                                <td>
                                    <form class="form-inline" method="post"
                                          action="${pageContext.request.contextPath}/admin/change_request_state">
                                        <div class="form-group mx-sm-3 mb-2">
                                            <input type="hidden" name="userId" value="<c:out value="${r.user.id}"/>">
                                            <input type="hidden" name="facultyId"
                                                   value="<c:out value="${r.faculty.id}"/>">
                                            <select name="state" id="chooseState" class="form-control form-control-sm">
                                                <c:forEach var="s" items="${applicationScope.request_states}">
                                                    <option value="<c:out value="${s.state}"/>">
                                                        <fmt:message key="${s.key}"/>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                            <button type="submit" class="btn btn-outline-primary btn-sm"
                                                    style="margin-left: 5px;"><fmt:message
                                                    key="form.change.request.button"/>
                                            </button>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <fmt:message key="message.info.requests.nothing"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>