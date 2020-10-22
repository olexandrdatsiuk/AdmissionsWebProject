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
    <title><fmt:message key="title.update.faculty"/></title>
</head>
<body>
<%@include file="/WEB-INF/jspf/admin/navbar.jspf" %>
<br>
<h3><fmt:message key="title.update.faculty"/></h3><br>

<div class="col-md-4">
    <div class="tab-content profile-tab" id="myTabContent">
        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <c:choose>
                <c:when test="${not empty pageContext.request.getAttribute(\"faculties\")}">
                    <form method="post" action="${pageContext.request.contextPath}/admin/update_faculty">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label for="inputFaculty"><fmt:message key="form.label.faculty"/></label>
                                <select name="faculty" id="inputFaculty" class="form-control">
                                    <c:forEach var="f" items="${pageContext.request.getAttribute(\"faculties\")}">--%>
                                        <option value="<c:out value="${f.id}"/>"><c:out value="${f.name}"/></option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="university"
                                       value="${pageContext.request.getParameter("university")}">
                                <label for="inputFreeAmount"><fmt:message
                                        key="form.manage.university.add.label.budget.amount"/></label>
                                <input type="text" name="free_amount" class="form-control" id="inputFreeAmount"
                                       placeholder="<fmt:message key="form.manage.university.add.label.budget.amount"/>">
                                <label for="inputAllAmount"><fmt:message
                                        key="form.manage.university.add.label.all.amount"/></label>
                                <input type="text" name="all_amount" class="form-control" id="inputAllAmount"
                                       placeholder="<fmt:message key="form.manage.university.add.label.all.amount"/>">
                            </div>
                        </div>
                        <div style="text-align: center;">
                            <button type="submit" class="btn btn-outline-primary"><fmt:message
                                    key="form.manage.university.button.update"/></button>
                        </div>
                    </form>
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