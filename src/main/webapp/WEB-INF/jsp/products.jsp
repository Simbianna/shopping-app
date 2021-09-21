<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Каталог товаров</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
<h1 align="center">Каталог товаров</h1>
<div class="container">
    <form action="/shop/products" method="get" class="form-horizontal">
        <br>
        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input type="text" name="input" placeholder="Наименование"
                   class="form-control"/>
        </div>
        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input name="minPrice" placeholder="Минимальная цена"
                   class="form-control"/>
        </div>
        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input name="maxPrice" placeholder="Максимальная цена"
                   class="form-control"/>
        </div>

        <div style="margin-top: 10px" class="form-group">
            <div class="col-sm-6 controls">
                <button type="submit" class="btn btn-primary">
                    <c:choose>
                        <c:when test="${filter.length()>0}">
                           Сбросить фильтр
                        </c:when>
                        <c:otherwise>Фильтровать</c:otherwise>
                    </c:choose>
                    </button>
            </div>
        </div>
    </form>

    <c:choose>
        <c:when test="${filter.length()>0}">
            <jsp:text>Отфильтровано по: ${filter}</jsp:text>
        </c:when>
    </c:choose>
    <br>

    <a href="/products/add">Добавить товар</a>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Название товара</th>
            <th>Цена</th>
            <th align="center" colspan="3" scope="colgroup">Действия</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.title}</td>
                <td>${product.price}</td>
                <td><a type="button" class="btn btn-success" href="/products/edit/${product.id}">Редактировать</a></td>
                <td><a type="button" class="btn btn-warning" href="/products/remove/${product.id}">Удалить</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>