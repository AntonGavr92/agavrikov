<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/styles.css">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="${pageContext.servletContext.contextPath}/js/script.js"></script>
    <title>Title</title>
</head>
<body>
    Фильтр:
    <select class="select-filter" name="" onchange="setFilter('${pageContext.servletContext.contextPath}')">
        <option value="all">All</option>
        <option value="true">Only done</option>
        <option value="false">Only not done</option>
    </select>
    <div class="users-list">
        <div class="row">
            <div class="inline">Description</div>
            <div class="inline">Created</div>
            <div class="inline">Done</div>
        </div>
        <div class="data-items">
            <c:forEach items="${itemsList}" var="item">
                <div class="row">
                    <div class="inline">${item.desc}</div>
                    <div class="inline">${item.created}</div>
                    <div class="inline">${item.done}</div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="create-item">
        <form action="${pageContext.servletContext.contextPath}/" method="post" onsubmit="createItem('${pageContext.servletContext.contextPath}')">
            <input type="text" class="descr" name="descr" placeholder="Description"/>
            Done: <input class="done" type="checkbox" name="done">
            <input type="submit" value="Create item"/>
        </form>
    </div>
</body>
</html>
