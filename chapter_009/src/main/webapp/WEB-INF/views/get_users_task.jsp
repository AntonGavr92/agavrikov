<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/get_users.css"/>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/task_script.js"></script>
    <title>Users</title>
</head>
<body>
<c:if test="${!empty users}">
    <table class="admin-table" border="1" style="border-collapse: collapse;">
        <tr>
            <td>Имя</td>
            <td>Email</td>
            <td>Логин</td>
            <td>Музыкальные типы</td>
            <td>Роль</td>
            <td>Адрес</td>
            <td></td>
            <c:if test="${userIsAdmin == true}">
                <td></td>
            </c:if>
        </tr>
        <c:forEach items="${users}" var="user">
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td>
                    <c:forEach items="${user.musicTypes}" var="musicType">
                        <div>
                            <c:out value="${musicType.type}"></c:out>
                        </div>
                    </c:forEach>
                </td>
                <td>
                    <c:out value="${user.role.name}"></c:out>
                </td>
                    <td>
                        <c:out value="${user.adress.address}"></c:out>
                    </td>
                <td><a class="edit-link" href="${pageContext.servletContext.contextPath}/task/update?id=<c:out value="${user.id}"></c:out>">Редактировать</a></td>
                <c:if test="${userIsAdmin == true}">
                    <td class="padding-0">
                        <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/task/list' method='post'>
                            <input class="delete-user" type='submit' value='Удалить'>
                            <input type='hidden' name='userId' value='<c:out value="${user.id}"></c:out>'>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        <tr>
            <c:if test="${userIsAdmin == true}">
                <td colspan="8" style="text-align: center;"><a class="add-link" href='${pageContext.servletContext.contextPath}/task/update'>Создать</a></td>
            </c:if>
        </tr>
    </table>
</c:if>
<c:if test="${userIsAdmin == true}">
    <br>
    <br>
    <div>
        <select class="filter_en" onchange="changeFilter('${pageContext.servletContext.contextPath}')">
            <c:forEach items="${filters}" var="filter">
                <option <c:if test="${param.filterId == filter.id}">selected</c:if> value="<c:out value="${filter.id}"></c:out>"><c:out value="${filter.name}"></c:out></option>
            </c:forEach>
        </select>
        <select class="filter_val">
            <c:forEach items="${filterValues}" var="filterValues">
                <option value="<c:out value="${filterValues.id}"></c:out>"><c:out value="${filterValues.name}"></c:out></option>
            </c:forEach>
        </select>
        <button onclick="setFilter('${pageContext.servletContext.contextPath}')">Применить фильтр</button>
    </div>
</c:if>
</body>
</html>
