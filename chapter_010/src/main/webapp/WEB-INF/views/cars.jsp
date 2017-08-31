<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars shop</title>
</head>
<body>
    <table class="cars-list">
        <tr>
            <form method="post" action="${pageContext.servletContext.contextPath}/cars/">
                <td><input type="text" name="filter_name"/></td>
                <td>
                    <select name="filter_transmission">
                        <c:forEach items="${transmissions}" var="transmission">
                            <option value="${transmission.id}">${transmission.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="filter_engine">
                        <c:forEach items="${engines}" var="engine">
                            <option value="${engine.id}">${engine.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="filter_gear_shift">
                        <c:forEach items="${gearShifts}" var="gearShift">
                            <option value="${gearShift.id}">${gearShift.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="submit" value="Apply filters"></td>
            </form>
        </tr>
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