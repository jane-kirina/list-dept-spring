<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>New employee</title>
</head>
<style>
    <%@include file='../../../style/table.css' %>
    <%@include file='../../../style/main.css' %>
    <%@include file='../../../style/form.css' %>
</style>

<body>
<div id="container">
    <div id="header">
        New employee
    </div>
    <div id="content">
        <br>
        <%--@elvariable id="employeePojo" type="com.example.model.EmployeePojo"--%>
        <form:form action="/app/department/${deptId}/employee/create-data" method="post" modelAttribute="employeePojo">
            <div class="container">
                <div class="container error">
                    <form:errors element="employeePojo"/>
                    <form:errors path="name"/>
                    <form:errors path="yearsWorking"/>
                    <form:errors path="email" />
                    <form:errors path="birthDate"/>
                    <form:errors path="department"/>
                </div>

                <hr>
                <form:label path="name" class="label">Name of employee</form:label>
                <form:input path="name" type="text" placeholder="Name" required="required"/>

                <form:label path="yearsWorking" class="label">How many years has it been working</form:label>
                <form:input path="yearsWorking" type="number" placeholder="Experience" required="required"/>
                <br>
                <br>

                <form:label path="email" class="label">Email</form:label>
                <form:input path="email" type="text" placeholder="Email" required="required"/>

                <form:label path="birthDate" class="label">Birthday</form:label>
                <form:input path="birthDate" type="date" placeholder="Date Of Birth" required="required"/>

                <form:input path="department.id" type="hidden" value="${deptId}"/>
                <hr>

                <button type="submit" class="add">Add</button>
            </div>

            <div class="container back">
                <p>Back to <a href="<spring:url value='/app/department'/>">list of departments</a>
                    or <a href="<spring:url value='/app/department/${deptId}/employee'/>">list of employee in ${deptId} dept</a>.</p>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>