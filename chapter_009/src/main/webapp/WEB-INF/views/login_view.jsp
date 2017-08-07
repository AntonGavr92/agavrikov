<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${!empty error}">
    <div>${error}</div>
</c:if>

    <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/auth' method='post'>
        Логин : <input type='text' name='login'></br></br>
        Пароль : <input type='password' name='password'></br></br>
        <input type='submit' value='Войти'>
    </form>
</body>
</html>
