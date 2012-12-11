<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" media="all" href="style.css" />
    <script type="text/javascript" src="js/jquery/jquery.js"></script>
    <script type="text/javascript" src="js/registration.js"></script>
</head>
<body>

    <form:form method="post" commandName="user" action="newuser.html" cssClass="registerWraper">
        <h2>Register new user account:</h2>
    <table>
        <tr>
            <td class="item1"><p>Login:</p><p class="required">*</p></td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td class="item1"><p>Password:</p><p class="required">*</p></td>
            <td><form:password path="password" /></td>
            <td><form:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td class="item1"><p>Confirm Password:</p><p class="required">*</p></td>
            <td><form:password path="confirmPassword" /></td>
            <td><form:errors path="confirmPassword" cssClass="error" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Register"></td>
            <td> <a href="index.html" >Back</a>
                <%--<button id="back" onclick="window.history.back()">Back</button>--%>
            </td>
            <td><h6>Fields marked wis</h6> <p class="required">*</p> <h6>is rquired</h6></td>
        </tr>
    </table>
    </form:form>
</body>
</html>