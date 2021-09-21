<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>
<body>
<div>
<H1></H1>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/shop/login">Войти</a></h4>
        <h4><a href="/shop/registration">Зарегистрироваться</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/shop/logout">Выйти</a></h4>
    </sec:authorize>
    <h4><a href="/shop/products">Начать шоппинг</a></h4>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h4><a href="/shop/admin">Пользователи </a></h4>
    </sec:authorize>
</div>
</body>
</html>