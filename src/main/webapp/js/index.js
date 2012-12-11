function printHistoryTable(json) {
	table = $('#table-history');
	$("tbody", table).remove();
	table
			.append('<tr class="historyHead"><th>id</th><th>Expression</th><th>Result</th><th>Is exp valid</th></tr>');
	for (index = 0; index < json.length; index++) {
		el = json[index];
		row = '<tr><td>' + el.id + '</td><td>' + el.expression + ' </td><td>'
				+ el.result + ' </td><td>' + el.isValid + ' </td>';
		table.append(row);
	}
}
function updateHistory() {
	valid = $('#historyResults').val();
	$.ajax({
		url : 'getHistoryList.html',
		data : ({
			valid: valid
		}),	
		success : function(data) {
			json = JSON.parse(data);
			printHistoryTable(json);
		}
	});
}
function printResult(data) {
	$('#expression-result').html("<h1>Result:</h1><p>" + data + "</p>");
	if ($('#clearInput').val() == "true") {
		$('#expInput').val("");
	}
}
function calculateExpression() {
	var exp = $('#expInput').val();
	$.ajax({
		url : 'calculateExpression.html',
		data : ({
			expression : exp
		}),
		success : function(data) {
			printResult(data);
		}
	});
}