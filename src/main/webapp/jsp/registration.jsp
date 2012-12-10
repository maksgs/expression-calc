<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Registration</title>
    <%--<link rel="stylesheet" type="text/css" media="all" href="style.css" />--%>
    <script type="text/javascript" src="js/jquery/jquery.js"></script>
    <script type="text/javascript" src="js/registration.js"></script>
</head>
<body>

    <form:form method="post" commandName="user" action="newuser.html">
    <%--<form action="/newuser.html" >--%>
    <table>
        <tr>
            <td>Login</td>
            <td><form:input path="name" /></td>
                <%--<td><input id="login" type="text" name="name" max_length="20" size="20" /> </td>--%>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <%--<td><input type='password'  name="pwd1" id="pwd1" value="<c:out value="${pwd1}"/>" /></td>--%>
            <td><form:password path="password" /></td>
            <td><form:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td>Confirm Password :</td>
            <%--<td><input type='password'  name="pwd2" id="pwd2" value="<c:out value="${pwd2}"/>" /></td>--%>
            <td><form:password path="confirmPassword" /></td>
            <td><form:errors path="confirmPassword" cssClass="error" /></td>
        </tr>
        <tr>
            <%--<td><button onclick="registerUser();" >Register</button></td>--%>
            <td><input type="submit" value="REGISTER"></td>
            <%--<td><button id="back" onclick="window.history.back()">Back</button> </td>--%>
        </tr>
    </table>
    <%--</form>--%>
    </form:form>
                                                                                </body>
</html>