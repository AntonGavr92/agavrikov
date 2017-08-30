<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create car</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data" action="${pageContext.servletContext.contextPath}/cars/create_car/">
        Name:<input type="text" name="name">
        User_id:<input type="text" name="user" value="1">
        <select name="transmission">
            <c:forEach items="${transmissions}" var="transmission">
                <option value="${transmission.id}">${transmission.name}</option>
            </c:forEach>
        </select>
        <select name="engine">
            <c:forEach items="${engines}" var="engine">
                <option value="${engine.id}">${engine.name}</option>
            </c:forEach>
        </select>
        <select name="gear-shift">
            <c:forEach items="${gearShifts}" var="gearShift">
                <option value="${gearShift.id}">${gearShift.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Create">
        Picture: <input type="file" name="picture">
    </form>
</body>
</html>
