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
    <title><fmt:message key="title.blocking"/></title>
</head>
<body>
<%@include file="/WEB-INF/jspf/admin/navbar.jspf" %>
<br>
<h3><fmt:message key="title.blocking.h"/></h3><br>
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
            <form method="post" action="${pageContext.request.contextPath}/admin/block_user">
                <div class="form-row">
                    <div class="form-group col-md-7">
                        <label for="inputScore"><fmt:message key="form.label.email"/></label>
                        <input type="text" name="email" class="form-control" id="inputScore"
                               placeholder="<fmt:message key="form.label.email"/>">
                    </div>
                </div>
                <div style="text-align: left;">
                    <button type="submit" class="btn btn-outline-primary"><fmt:message
                            key="form.block.user.button"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
