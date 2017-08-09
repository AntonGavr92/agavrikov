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
    <style>
        td {
            padding:4px 7px;
        }

        .add-link:hover {
            background: #5fef84;
        }

        .add-link {
            display: block;
            padding: 4px 7px;
            text-decoration: none;
            color: #000000;
        }

        .edit-link {
            display: block;
            padding: 4px 7px;
            text-decoration: none;
            color: #000000;
        }

        .admin-table tr:last-child td{
            padding: 0;
        }

        .delete-user {
            background: none;
            border: none;
            outline: none;
            display: block;
            cursor: pointer;
        }
        .padding-0 {
            padding: 0;
        }
    </style>
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
