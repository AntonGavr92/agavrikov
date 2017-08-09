<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/login_view.css"></link>
    <title>Title</title>
</head>
<body>
<c:if test="${!empty error}">
    <div>${error}</div>
</c:if>

    <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/auth' method='post'>
        <input placeholder="Логин" type='text' name='login'></br></br>
        <input placeholder="Парооль" type='password' name='password'></br></br>
        <input class="submit" type='submit' value='Войти'>
    </form>
</body>
</html>
