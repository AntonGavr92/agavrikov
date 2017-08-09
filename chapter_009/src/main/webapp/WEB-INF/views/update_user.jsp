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
            <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/update_user?id=${user.id}' method='post' onsubmit="validate();">
                <div class="main-fields">
                    <b>Форма редактирования пользователя</b></br></br>
                    <input placeholder="Имя" type='text' name='nameUser' value='${user.name}'></br></br>
                    <input placeholder="Почта" type='text' name='emailUser' value='${user.email}'></br></br>
                    <input placeholder="Логин" type='text' name='loginUser' value='${user.login}'></br></br>
                    <input placeholder="Пароль" type='password' name='userPassword' value='${user.password}'></br></br>
                </div>
                <div class="cities">
                    <c:forEach items="${cities}" var="city">
                        <div>
                            <div class="city city-block">${city.name}</div>
                            <div class="country city-block">${city.country}</div>
                            <div class="check"><input type="radio" <c:if test="${user.city.id == city.id}">checked</c:if> value="${city.id}" name="active_city"/></div>
                        </div>
                    </c:forEach>
                </div>
                <div class="create-city">
                    <div class="city city-block"><input type="text"/></div>
                    <div class="country city-block"><input type="text"/></div>
                    <div class="add"><button onclick="createCity('${pageContext.servletContext.contextPath}');">Добавить город</button></div>
                </div>

                <c:if test="${isAdmin}">
                    Роль : <select name="role">
                        <c:forEach items="${roles}" var="role">
                            <option value="<c:out value="${role.key}"></c:out>"><c:out value="${role.value}"></c:out></option>
                        </c:forEach>
                     </select>
                </c:if>
                <input class="submit" type='submit' value='Сохранить'>
                <input type='hidden' name='userId' value='${user.id}'>
                <input type='hidden' name='id_city' value='${user.city}'>
            </form>
        </c:when>
        <c:when test="${empty user && isAdmin}">
            <form style='margin-bottom: 0;' action='${pageContext.servletContext.contextPath}/update_user' onsubmit="validate();" method='post'>
                <b>Форма создания пользователя</b></br></br>
                <div class="main-fields">
                    <input placeholder="Имя" type='text' name='nameUser'></br></br>
                    <input placeholder="Почта" type='text' name='emailUser'></br></br>
                    <input placeholder="Логин" type='text' name='loginUser'></br></br>
                    <input placeholder="Пароль" type='password' name='userPassword'></br></br>
                </div>
                <div class="cities">
                    <c:forEach items="${cities}" var="city">
                        <div>
                            <div class="city city-block">${city.name}</div>
                            <div class="country city-block">${city.country}</div>
                            <div class="check"><input value="${city.id}" type="radio" name="active_city"/></div>
                        </div>
                    </c:forEach>
                </div>
                <div class="create-city">
                    <div class="city city-block"><input type="text"/></div>
                    <div class="country city-block"><input type="text"/></div>
                    <div class="add"><button onclick="createCity('${pageContext.servletContext.contextPath}');">Добавить город</button></div>
                </div>
                <select name="role">
                         <c:forEach items="${roles}" var="role">
                             <option value="<c:out value="${role.key}"></c:out>"><c:out value="${role.value}"></c:out></option>
                         </c:forEach>
                        </select>
                <input class="submit" type='submit' value='Сохранить'>
                <input type='hidden' name='id_city' value='${user.city}'>
            </form>
        </c:when>
    </c:choose>
</body>
</html>
