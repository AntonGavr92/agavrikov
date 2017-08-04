<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.SimpleUserManager" %>
<%--
  Created by IntelliJ IDEA.
  User: gavrikov.a
  Date: 04/08/2017
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%SimpleUserManager userManger = new SimpleUserManager();%>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <%for (User user : userManger.getAllUsers()) {%>
    <tr>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getLogin()%></td>
        <td><a href="<%=request.getContextPath()%>/update_user/?id=<%=user.getId()%>">Редактировать пользователя</a></td>
        <td>
            <form style='margin-bottom: 0;' action='<%=request.getContextPath()%>/users' method='post'>
                <input type='submit' value='Удалить пользователя'>
                <input type='hidden' name='userId' value='<%=user.getId()%>'>
            </form>
        </td>
    </tr>
    <%}%>
</table>
</br><a href='<%=request.getContextPath()%>/update_user/'>Создать пользователя</a>
</body>
</html>
