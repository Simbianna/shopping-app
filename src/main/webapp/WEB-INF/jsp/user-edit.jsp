<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Редактирование товара</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<a type="button" class="btn btn-default"
   href="${pageContext.request.contextPath}/">Главная
</a>
<br>
<div>
    <c:choose>
        <c:when test="${user.id == null}">
            <h3>Добавление пользователя</h3>
        </c:when>
        <c:otherwise>
            <h3>Изменение пользователя</h3>
        </c:otherwise>
    </c:choose>


    <form:form method="POST" modelAttribute="user" >

        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            ID: ${user.id} <%--<form:form path="id" placeholder="id"
        ></form:form>--%>
        </div>

        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            Логин: <form:input type="text" path="username" placeholder="Логин"
                                  autofocus="true"></form:input>
        </div>

        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            Имя: <form:input type="text" path="name" placeholder="Имя"
                              autofocus="true"></form:input>
        </div>

        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            Пароль: <form:input type="text" path="password" placeholder="Пароль"
                              autofocus="true"></form:input>
        </div>

        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            Почта: <form:input type="text" path="email" placeholder="Почта"
                                autofocus="true"></form:input>
        </div>

        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            Роли: <form:checkboxes path="roles" items="${allRoles}"></form:checkboxes>
        </div>

        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            Аккаунт активен: <form:checkbox path="enabled"></form:checkbox>
        </div>
        <button type="submit" class="btn btn-success">Подтвердить</button>
    </form:form>
</div>
</body>
</html>
