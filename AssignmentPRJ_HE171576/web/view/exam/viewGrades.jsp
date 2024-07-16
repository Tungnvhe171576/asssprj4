<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Course</title>
</head>
<body>
    <c:if test="${not empty requestScope.courses}">
        <form action="viewGrades" method="POST">
            <input type="hidden" name="sid" value="${param.sid}"/>
            <label for="cid">Course:</label>
            <select name="cid" id="cid">
                <c:forEach items="${requestScope.courses}" var="c">
                    <option value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="View Grades"/>
        </form>
    </c:if>
</body>
</html>
