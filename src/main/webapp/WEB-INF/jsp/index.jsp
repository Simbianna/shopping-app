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
    <c:choose>
        <c:when test="${pageContext.request.userPrincipal != null}">
            <h3>Добро пожаловать, ${pageContext.request.userPrincipal.name}!</h3>
        </c:when>
        <c:otherwise>
            <h3>Для продолжения необходимо войти</h3>
        </c:otherwise>
    </c:choose>

    <sec:authorize access="!isAuthenticated()">
        <h4><a href="${pageContext.request.contextPath}/login">Войти</a></h4>
        <h4><a href="${pageContext.request.contextPath}/registration">Зарегистрироваться</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="${pageContext.request.contextPath}/logout">Выйти</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
    <h4><a href="${pageContext.request.contextPath}/products">К списку товаров</a></h4>
    </sec:authorize>
    <sec:authorize access="hasAnyAuthority('ADMIN')">
        <h4><a href="${pageContext.request.contextPath}/admin">Администирование</a></h4>
    </sec:authorize>
</div>
</body>
</html>