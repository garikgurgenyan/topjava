<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <style>
        .red{
            color: red;
        }
        .green{
            color: green;
        }
    </style>
</head>
<body>
<table style=" border-style: solid; border-width:1px; width: 600px; border-collapse: collapse;">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 30px;">dateTime</td>
        <td style="width: 80px;">description</td>
        <td style="width: 80px;">calories</td>
        <td style="width: 80px;">exceed</td>
    </tr>
    </thead>
    <c:forEach var="mea" items="${meaList}">
        <c:choose>
            <c:when test="${mea.isExceed()}">
                <tr class="red">
            </c:when>
            <c:when test="${!mea.isExceed()}">
                <tr class="green">
            </c:when>
        </c:choose>

            <td>${mea.getDateTimeForPrint()}</td>
            <td><c:out value="${mea.getDescription()}" /></td>
            <td><c:out value="${mea.getCalories()}" /></td>
            <td><c:out value="${mea.isExceed()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
