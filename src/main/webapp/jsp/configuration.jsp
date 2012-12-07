<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="all" href="style.css" />
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/configuration.js"></script>
<title>Configure Project</title>
</head>
<body class="confBody">
	<div>
		<script>
		//set current configurations
		$(document).ready(function () {
			$("#expressionLength").val(${expressionLength});
			$("#historyLength").val(${historyLength});
			$("#historyAutofreshMil").val(${historyAutofreshMil});
			$("#saveHistory").attr('checked', ${saveHistory});
			$("#clearInputAfterCalculation").attr('checked', ${clearInputAfterCalculation});
			$("#isCachingUsed").attr('checked', ${isCachingUsed});
			$("#saveDuplicatedExpToHistory").attr('checked', ${saveDuplicatedExpToHistory});
		});
		</script>
	<h1>Configure Project</h1>
	<p>Expression Max Length:	<select id="expressionLength">
		   <option value="20">20</option>
		   <option value="50">50</option>
		   <option value="100">100</option>
		   <option value="200">200</option>
 		</select>
 	</p>
 	<p>Number of History rows	<select id="historyLength">
		   <option value="20">20</option>
		   <option value="50">50</option>
		   <option value="100">100</option>
		   <option value="200">200</option>
		   <option value='0'>All</option>
 		</select>
 	</p>		
 	 	<p>Refresh history each:	<select id="historyAutofreshMil">
		   <option value="10000">10 sec</option>
		   <option value="20000">20 sec</option>
		   <option value="30000">30 sec</option>
		   <option value="60000">60 sec</option>
		   <option value='300000'>300 sec</option>
 		</select>
 	</p>
 	<p>Save history?
 	<input type="checkbox" id="saveHistory" />
 	</p>
 	<p>Clear Input After Calculation?
 	<input type="checkbox" id="clearInputAfterCalculation" />
 	</p>
 	<p>Use caching ? (expressions from history will not be calculated once again) 
 	<input type="checkbox" id="isCachingUsed" />
 	</p>
 	<p>Save duplicated expressions to history?
 	<input type="checkbox" id="saveDuplicatedExpToHistory" />
 	</p>		
 	<p><input type="button" onclick="saveConfiguration();" value="Save"></p>
	</div>
	<div id="save-result"></div>
	<div><a href="index.html" >Back to Main Page</a></div>
</body>
</html>