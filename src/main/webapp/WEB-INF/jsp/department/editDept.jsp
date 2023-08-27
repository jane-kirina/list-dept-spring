<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Edit Dept ${id}</title>
</head>
<style>
    <%@include file='../../../style/table.css' %>
    <%@include file='../../../style/main.css' %>
    <%@include file='../../../style/form.css' %>
</style>

<body>
<div id="container">
    <div id="header">
        Edit Department
    </div>
    <div id="content">
        <br>
        <%--@elvariable id="departmentPojo" type="com.example.model.DepartmentPojo"--%>
        <form:form action="/app/department/update-data/${id}" method="post" modelAttribute="departmentPojo">
            <div class="container">
                <div class="container error">
                    <form:errors element="departmentPojo"/>
                    <form:errors path="name"/>
                    <form:errors path="number"/>
                </div>

                <hr>
                <form:label path="name" class="label">Name of new department</form:label>
                <form:input path="name" type="text" placeholder="Enter unique name" equired="required"/>

                <form:label path="number" class="label">Department phone number</form:label>
                <form:input path="number" type="text" placeholder="Phone" required="required"/>
                <hr>

                <form:input path="id" type="hidden"/>

                <button type="submit" class="add">Edit</button>
            </div>

            <div class="container back">
                <p>Back to <a href="<spring:url value='/app/department'/>">list of departments</a>.</p>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>