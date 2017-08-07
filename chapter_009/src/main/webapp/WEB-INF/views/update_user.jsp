<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add/update user</title>
</head>
<body>
    <c:choose>
        <c:when test="${!empty user}">
            Форма редактирования пользователя</br></br>
            <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/update_user?id=${user.id}' method='post'>
                Имя : <input type='text' name='nameUser' value='${user.name}'></br></br>
                Почта : <input type='text' name='emailUser' value='${user.email}'></br></br>
                Логин : <input type='text' name='loginUser' value='${user.login}'></br></br>
                Пароль : <input type='password' name='userPassword' value='${user.password}'></br></br>
                <c:if test="${isAdmin}">
                    Роль : <select name="role">
                        <c:forEach items="${roles}" var="role">
                            <option value="<c:out value="${role.key}"></c:out>"><c:out value="${role.value}"></c:out></option>
                        </c:forEach>
                     </select>
                </c:if>
                <input type='submit' value='Сохранить'>
                <input type='hidden' name='userId' value='${user.id}'>
            </form>
        </c:when>
        <c:when test="${empty user && isAdmin}">
            Форма создания пользователя</br></br>
            <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/update_user' method='post'>
                Имя : <input type='text' name='nameUser'></br></br>
                Почта : <input type='text' name='emailUser'></br></br>
                Логин : <input type='text' name='loginUser'></br></br>
                Пароль : <input type='password' name='userPassword'></br></br>
                Роль : <select name="role">
                         <c:forEach items="${roles}" var="role">
                             <option value="<c:out value="${role.key}"></c:out>"><c:out value="${role.value}"></c:out></option>
                         </c:forEach>
                        </select>
                <input type='submit' value='Сохранить'>
            </form>
        </c:when>
    </c:choose>
</body>
</html>
