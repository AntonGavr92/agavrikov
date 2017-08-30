<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars shop</title>
</head>
<body>
<table class="cars-list">
    <tr class="row">
        <td>Name car</td>
        <td>Transmission</td>
        <td>Engine</td>
        <td>Gear shift</td>
        <td>Picture</td>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>${car.name}</td>
            <td>${car.transmission.name}</td>
            <td>${car.engine.name}</td>
            <td>${car.gearShift.name}</td>
            <td><img style="max-height:50px;" src="${car.picturePath}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.servletContext.contextPath}/cars/create_car/">Create car</a>
</body>
</html>