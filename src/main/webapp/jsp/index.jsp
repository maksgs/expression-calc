<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="all" href="style.css" />
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<title>Calculate Expression</title>
</head>
<body>
<div class="mainMenu">

    <a href="/j_spring_security_logout" />Logout</a>
    <a href="configuration.html" >Configure project</a>
    <a href="#">${userName}</a>
</div>
	<div>
		<h1>Calculate your expression:</h1>
		<input type="hidden" id="clearInput" value="${clearInputAfterCalculation}" />
		<p><input id="expInput" type="text" name="expression" max_length="${expressionLength}" size="100" /></p>
		<p><button onclick="calculateExpression();" >Calculate</button></p>
	</div>
	<div>
		<p>Show: <select id="historyResults">
			   <option value="0">Only Incorrect Expressions</option>
			   <option value="1">Only Valid Expressions</option>
			   <option value="2" selected>ALL</option>
	 		</select>
	 	</p>
		<table id="table-history" style="border:1px solid blue;width:400px;text-align:center;">
			<tbody />  
		</table>
	</div>
	<div id="expression-result"></div>
	<%--<div><a href="configuration.html" >Configure project</a></div>--%>
	<script>
	$(document).ready(function () {
		updateHistory();
		setInterval( "updateHistory()", ${historyAutofreshMil} );
	});
	</script>
</body>
</html>