<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/update_user.css"></link>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/script.js"></script>
    <title>Add/update user</title>
</head>
<body>
<c:choose>
    <c:when test="${!empty user}">
        <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/task/update?id=${user.id}' method='post' onsubmit="validate();">
            <div class="main-fields">
                <b>Форма редактирования пользователя</b></br></br>
                <input placeholder="Имя" type='text' name='nameUser' value='${user.name}'></br></br>
                <input placeholder="Почта" type='text' name='emailUser' value='${user.email}'></br></br>
                <input placeholder="Логин" type='text' name='loginUser' value='${user.login}'></br></br>
                <input placeholder="Адрес" type='text' name='addressUser' value='${user.adress.address}'></br></br>
                <input placeholder="Пароль" type='password' name='userPassword' value='${user.password}'></br></br>
            </div>
            <c:forEach items="${musicTypes}" var="musicType">
                <c:out value="${musicType.type}"></c:out><input type="checkbox" name="musicType" value="${musicType.id}">
            </c:forEach>
            <c:if test="${isAdmin}">
                Роль : <select name="role">
                <c:forEach items="${roles}" var="role">
                    <option value="<c:out value="${role.id}"></c:out>"><c:out value="${role.name}"></c:out></option>
                </c:forEach>
            </select>
            </c:if>
            <input class="submit" type='submit' value='Сохранить'>
            <input type='hidden' name='userId' value='${user.id}'>
        </form>
    </c:when>
    <c:when test="${empty user && isAdmin}">
        <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/task/update' onsubmit="validate();" method='post'>
            <b>Форма создания пользователя</b></br></br>
            <div class="main-fields">
                <input placeholder="Имя" type='text' name='nameUser'></br></br>
                <input placeholder="Почта" type='text' name='emailUser'></br></br>
                <input placeholder="Логин" type='text' name='loginUser'></br></br>
                <input placeholder="Адрес" type='text' name='addressUser'></br></br>
                <input placeholder="Пароль" type='password' name='userPassword'></br></br>
            </div>
            <c:forEach items="${musicTypes}" var="musicType">
                <c:out value="${musicType.type}"></c:out><input type="checkbox" name="musicType" value="${musicType.id}">
            </c:forEach>
            <select name="role">
                <c:forEach items="${roles}" var="role">
                    <option value="<c:out value="${role.id}"></c:out>"><c:out value="${role.name}"></c:out></option>
                </c:forEach>
            </select>
            <input class="submit" type='submit' value='Сохранить'>
        </form>
    </c:when>
</c:choose>
</body>
</html>
