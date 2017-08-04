<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: gavrikov.a
  Date: 04/08/2017
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><a href="${pageContext.servletContext.contextPath}/update_user?id=<c:out value="${user.id}"></c:out>">Редактировать пользователя</a></td>
            <td>
                <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/users' method='post'>
                    <input type='submit' value='Удалить пользователя'>
                    <input type='hidden' name='userId' value='<c:out value="${user.id}"></c:out>'>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</br><a href='${pageContext.servletContext.contextPath}/update_user'>Создать пользователя</a>
</body>
</html>
