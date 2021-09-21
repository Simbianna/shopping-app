<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="userForm">
        <h2>Регистрация</h2>
        <div>
            <form:input type="text" path="username" placeholder="Логин"
                        autofocus="true"></form:input>
         <%--   <form:errors path="username"></form:errors>
                ${usernameError}--%>
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
    <a href="/shop">Главная</a>
</div>
</body>
</html>