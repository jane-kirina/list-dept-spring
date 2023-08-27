<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Something went wrong</title>
</head>
<style>
    <%@include file='../../../style/main.css' %>
</style>
<body>
<div id="container">
    <div id="header">
        Error
    </div>
    <br>
    <div id="content">
        <h1>Sorry, something went wrong.</h1>
        <div class="container error">
                <h1>${error}</h1>
        </div>
        <hr>
        <h2>Back to <a href="<spring:url value='/app/department'/>">list of departments</a>.</h2>
    </div>
</div>
</body>
</html>
