function printResult(data) {
	$('#save-result').html("<h1>Configurations were saved!<h1>");
}
function saveConfiguration() {
	var expressionLength = $('#expressionLength').val();
	var historyLength = $('#historyLength').val();
	var historyAutofreshMil = $('#historyAutofreshMil').val();
	if ($('#saveHistory').attr('checked') == 'checked') {
		var saveHistory = true;
	} else {
		var saveHistory = false;
	}
	;
	if ($('#clearInputAfterCalculation').attr('checked') == 'checked') {
		var clearInputAfterCalculation = true;
	} else {
		var clearInputAfterCalculation = false;
	}
	;
	if ($('#isCachingUsed').attr('checked') == 'checked') {
		var isCachingUsed = true;
	} else {
		var isCachingUsed = false;
	}
	;
	if ($('#saveDuplicatedExpToHistory').attr('checked') == 'checked') {
		var saveDuplicatedExpToHistory = true;
	} else {
		var saveDuplicatedExpToHistory = false;
	}
	;
	$.ajax({
		method : 'get',
		url : 'saveConfiguration.html',
		data : ({
			expressionLength : expressionLength,
			historyLength : historyLength,
			historyAutofreshMil : historyAutofreshMil,
			saveHistory : saveHistory,
			clearInputAfterCalculation : clearInputAfterCalculation,
			isCachingUsed : isCachingUsed,
			saveDuplicatedExpToHistory : saveDuplicatedExpToHistory
		}),
		success : function(data) {
			printResult(data);
		}
	});
}