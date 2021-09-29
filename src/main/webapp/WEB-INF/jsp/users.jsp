<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<a type="button" class="btn btn-default"
   href="${pageContext.request.contextPath}/">Главная
</a>
<br>
<a type="button" class="btn btn-default"
   href="${pageContext.request.contextPath}/admin">Кабинет Администратора
</a>
<br>
<div>
    <table class="table table-hover">
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>Password</th>
        <th>Email</th>
        <th>Registration date</th>
        <th>Roles</th>
        <th>Enabled</th>
        <th colspan="" scope="colgroup">Actions</th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.email}</td>
                <td>${user.registrationDate}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.name()}; </c:forEach>
                </td>
                <td>${user.enabled}</td>
                    <%--<td>
                        <form action="${pageContext.request.contextPath}/admin/users" method="POST">
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <input type="hidden" name="action" value="delete"/>
                            <button type="submit">Delete</button>
                        </form>
                    </td>--%>
                <td>
                    <a type="button" class="btn btn-default"
                       href="${pageContext.request.contextPath}/admin/users/edit/${user.id}">Edit</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
