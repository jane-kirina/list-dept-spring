<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>List of departments</title>
</head>
<style>
    <%@include file='../../style/table.css' %>
    <%@include file='../../style/main.css' %>
    <%@include file='../../style/main.css' %>
</style>

<body>
<div id="container">
    <div id="header">
        Departments
    </div>
    <div id="content">
        <br>
        <table class="table">
            <thead>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th><a href="<spring:url value='/app/department/create'/>" class="button">Add department</a></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${departments}" var="departments">
            <tr>
                <td><c:out value="${departments.id}"/></td>
                <td><c:out value="${departments.name}"/></td>
                <td><c:out value="${departments.number}"/></td>
                <td>
                    <ul>
                        <li>
                            <a href="<spring:url value='/app/department/update/${departments.id}'/>">
                                <button class="button" type="submit">
                                    Edit
                                </button>
                            </a>
                        </li>
                        <li>
                        </li>
                        <li>
                            <a href="<spring:url value='/app/department/${departments.id}/employee'/>">
                                <button class="button" type="submit">
                                    List of employees
                                </button>
                            </a>
                        </li>
                        <li>
                            <form action="<spring:url value='/app/department/delete/${departments.id}'/>" method="post">
                                <button onclick="return confirm('Are you sure you want to do this? ' +
                                 'When a department is deleted, employees from this department are also deleted')"
                                        class="button" type="submit">
                                    Delete
                                </button>
                            </form>
                        </li>
                    </ul>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
