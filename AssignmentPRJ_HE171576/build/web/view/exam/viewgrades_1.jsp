<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Grades</title>
</head>
<body>
  
    <table border="1">
        <tr>
            <th>Assessment Name</th>
            <th>Weight</th>
            <th>Score</th>
        </tr>
        <c:forEach items="${requestScope.grades}" var="grade">
            <tr>
                <td>${grade.exam.assessment.name}</td>
                <td>${grade.exam.assessment.weight}</td>
                <td>${grade.score}</td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/exam/viewGrades" method="POST">
        <input type="hidden" name="sid" value="${param.sid}"/>
        <input type="hidden" name="cid" value="${param.cid}"/>
       
    </form>
</body>
</html>
