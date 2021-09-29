<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>

<body>
<a type="button" class="btn btn-default"
   href="${pageContext.request.contextPath}/shop">Главная
</a>
<br>

<div>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="username" placeholder="Логин"
                        autofocus="true"></form:input>
            <form:errors path="username"></form:errors>
                ${usernameError}
        </div>
        <div>
            <form:input type="name" path="name"
                        placeholder="Имя"></form:input>
        </div>
        <div>
            <form:input type="email" path="email"
                        placeholder="Email"></form:input>
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Пароль"></form:input>
        </div>

        <button type="submit">Зарегистрироваться</button>
    </form:form>
    <br>
</div>
</body>
</html>