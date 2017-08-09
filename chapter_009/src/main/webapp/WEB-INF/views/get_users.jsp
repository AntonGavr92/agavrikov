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
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/get_users.css"></link>
    <title>Users</title>
</head>
<body>
    <c:if test="${!empty users}">
    <table class="admin-table" border="1" style="border-collapse: collapse;">
        <tr>
            <td>Имя</td>
            <td>Email</td>
            <td>Логин</td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><a class="edit-link" href="${pageContext.servletContext.contextPath}/update_user?id=<c:out value="${user.id}"></c:out>">Редактировать</a></td>
                <td class="padding-0">
                    <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/users' method='post'>
                        <input class="delete-user" type='submit' value='Удалить'>
                        <input type='hidden' name='userId' value='<c:out value="${user.id}"></c:out>'>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" style="text-align: center;"><a class="add-link" href='${pageContext.servletContext.contextPath}/update_user'>Создать</a></td>
        </tr>
    </table>
    </c:if>
    <c:if test="${!empty user}">
        <table border="1" style="border-collapse: collapse;">
            <tr>
                <td>Имя</td>
                <td>Email</td>
                <td>Логин</td>
                <td></td>
            </tr>
            <tr>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><a class="edit-link" href="${pageContext.servletContext.contextPath}/update_user?id=<c:out value="${user.id}"></c:out>">Редактировать</a></td>
            </tr>
        </table>
    </c:if>
</body>
</html>
