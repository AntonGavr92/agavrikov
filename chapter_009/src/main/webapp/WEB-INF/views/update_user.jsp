<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <title>Add/update user</title>
    <style>
        input{
            padding: 3px 8px;
            outline: none;
            border-radius: 4px;
            border: 1px solid;
        }

        form {
            text-align: center;
        }

        .submit {
            cursor: pointer;
        }

        .error {
            background: #ff7878;
        }

        .cities, .create-city{
            max-width: 400px;
            margin: auto;
        }

        .cities .check {
            display: inline-block;
            width: 10%;
        }

        .cities .city-block, .create-city .city-block {
            display: inline-block;
            width: 44%;
            margin-top: 10px;
        }

        .city-block input{
            width: 100%;
        }
    </style>
    <script>
        function validate() {
            var result = true;
            $(".error").each(function(index) {
                $(this).removeClass("error");
            });

            $(".main-fields input").each(function(index) {
                if ($(this).val() == "") {
                    $(this).addClass("error");
                    result = false;
                }
            });
            if (!result) {
                event.preventDefault();
            }
            return result;
        }

        function createCity() {
            var valNewCity = $(".city input").val();
            var valNewCountry = $(".country input").val();
            if (valNewCity != "" && valNewCountry !="" ) {
                $(".city input").removeClass("error");
                $(".country input").removeClass("error");
                $.ajax({
                    url: '${pageContext.servletContext.contextPath}/add_city',
                    dataType : "json",
                    data : {country : valNewCountry, city : valNewCity},
                    type : "post",
                    success: function (data) {
                        var resStr = "<div>" +
                                     "<div class='city city-block'>"+ valNewCity +"</div>" +
                                     "<div class='country city-block'>"+ valNewCountry +"</div>" +
                                     "<div class='check'><input type='radio' checked value='" + data.id + "' name='active_city'/></div></div>";
                        $(".cities").append(resStr);
                    }
                });
            } else {
                if (valNewCity = "") {
                    $(".city input").addClass("error");
                }
                if (valNewCountry = "") {
                    $(".country input").addClass("error")
                }
            }
            event.preventDefault();
        }
    </script>
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
                    <div class="add"><button onclick="createCity();">Добавить город</button></div>
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
                    <div class="add"><button onclick="createCity();">Добавить город</button></div>
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
