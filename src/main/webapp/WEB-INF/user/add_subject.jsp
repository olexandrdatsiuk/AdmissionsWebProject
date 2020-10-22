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
    <title><fmt:message key="title.add.subject"/></title>
</head>
<body>
<%@include file="/WEB-INF/jspf/user/navbar.jspf" %>
<br>
<h3><fmt:message key="title.add.subject"/></h3><br>

<sql:sqlHandler task="getSubjectsPerUser" attr="subjects_per_user"/>

<sql:sqlHandler task="getSubjectsUserNotHave" attr="subjects_user_not_have"/>

<c:if test="${not empty sessionScope.info_message_activity}">
    <div class="row">
        <div class="col-md-12 text-center alert alert-primary" id="message" role="alert">
            <fmt:message key="${sessionScope.info_message_activity}"/>
            <c:remove var="info_message_activity" scope="session"/>
        </div>
    </div>
</c:if>
<div class="col-md-4">
    <div class="tab-content profile-tab" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <c:choose>
                <c:when test="${not empty requestScope.subjects_user_not_have}">
                    <form method="post" action="${pageContext.request.contextPath}/user/add_subject">
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label for="inputSubject"><fmt:message key="form.add.subject.label.subject"/></label>
                                <select name="subject" id="inputSubject" class="form-control">
                                    <c:forEach var="s" items="${requestScope.subjects_user_not_have}">
                                        <option value="<c:out value="${s.id}"/>"><c:out value="${s.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="inputScore"><fmt:message key="form.add.subject.label.score"/></label>
                                <input type="number" step="1" name="score" class="form-control" id="inputScore"
                                       placeholder="<fmt:message key="form.add.subject.label.score"/>">
                            </div>
                        </div>
                        <div style="text-align: center;">
                            <button type="submit" class="btn btn-outline-primary"><fmt:message
                                    key="form.add.subject.button.add"/></button>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <fmt:message key="message.info.subjects.nothing"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>