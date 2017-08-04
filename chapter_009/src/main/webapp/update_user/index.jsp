<%@ page import="ru.job4j.servlets.SimpleUserManager" %>
<%@ page import="ru.job4j.servlets.User" %><%--
  Created by IntelliJ IDEA.
  User: gavrikov.a
  Date: 04/08/2017
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SimpleUserManager userManger = new SimpleUserManager();
%>
<html>
<head>
    <title>Add/update user</title>
</head>
<body>
<%if (request.getParameter("id") != null) {
    User user = userManger.getUserById(Integer.parseInt(request.getParameter("id")));%>
    Форма редактирования пользователя</br></br>
    <form style='margin-bottom: 0;' action='<%=request.getContextPath()%>/update_user?id=<%=user.getId()%>' method='post'>
    Имя : <input type='text' name='nameUser' value='<%=user.getName()%>'></br></br>
    Почта : <input type='text' name='emailUser' value='<%=user.getEmail()%>'></br></br>
    Логин : <input type='text' name='loginUser' value='<%=user.getLogin()%>'></br></br>
    <input type='submit' value='Сохранить'>
    <input type='hidden' name='userId' value='<%=user.getId()%>'>
    </form>
<%} else {%>
    Форма создания пользователя</br></br>
    <form style='margin-bottom: 0;' action='<%=request.getContextPath()%>/update_user' method='post'>
    Имя : <input type='text' name='nameUser'></br></br>
    Почта : <input type='text' name='emailUser'></br></br>
    Логин : <input type='text' name='loginUser'></br></br>
    <input type='submit' value='Сохранить'>
    </form>
<%}%>
</body>
</html>
