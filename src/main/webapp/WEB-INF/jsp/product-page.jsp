<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Карточка товара</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<a type="button" class="btn btn-default"
   href="${pageContext.request.contextPath}/">Главная
</a>
<br>
<a type="button" class="btn btn-default"
   href="${pageContext.request.contextPath}/shop/products">К списку товаров
</a>
<br>
<table>
    <tr>
        <td>Название: </td>
        <td>${product.title}</td>
    </tr>
    <tr>
        <td>Цена: </td>
        <td>${product.price}</td>
    </tr>
</table>
<br>
</body>
</html>