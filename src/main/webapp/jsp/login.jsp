<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" media="all" href="style.css" />
    <style type="text/css">

    </style>
</head>
<body onload='document.f.j_username.focus();'>
<div id="login">
<h3>Login using your Name and Password:</h3>

<%--<c:if test="${not empty error}">--%>
    <%--<div class="errorblock">--%>
        <%--Your login attempt was not successful, try again.<br /> Caused :--%>
            <%--${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}--%>
    <%--</div>--%>
<%--</c:if>--%>
<form name='f' action="<c:url value='j_spring_security_check' />" method='POST' >

    <table>
        <tr>
            <td colspan="2"><div class="loginerror">
                <c:if test="${not empty error}">
                    Wrong Login or Password
                </c:if>
            </div></td>
        </tr>
        <tr>
            <td>User:</td>
            <td><input type='text' name='j_username' value=''>
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password' />
            </td>
        </tr>
        <tr>
            <td >

            <label class="forCheckbox" for='_spring_security_remember_me'>
                Remember me:
                <input type='checkbox' name='_spring_security_remember_me'/>
            </label>

            </td>
            <td align="center">
                <a href="/registration.html"><i>Registration</i></a>
            </td>
        </tr>
        <tr>
            <td align="center">
                <input name="Login" type="submit" value="Login" />
            </td>
            <td align="center">
                <input name="Clear" type="reset" value="Clear"/>
            </td>
        </tr>
    </table>

</form>
</div>
</body>
</html>