

/*$(document).ready(function() {

	$("#ZC_CHECK").change(function() {
		
		var $input = $(this);
		
		console.log($input.prop("checked"));
		
		if ($input.prop('checked')) {
			console.log("checked");
			window.alert("checked");
		} else {
			window.alert("not checked");
		}
	});
});*/


/*$(document).ready(function() {
	
	$('input[type=checkbox]').each( function() {
		
		if ($(this).prop('checked')) {
			console.log($(this).attr('id'));
		}
	});
	
});*/


$(document).ready(function() {
	
	// Initialization 
	$(".CHECK_INPUT").hide();
	$(".CHECK_SELECT").show();

	// Toggle between input and select
	$(':checkbox').change( function() {
		
		
		if ($(this).prop('checked')) {
			
			var id = $(this).attr('id');
			var inpDivId = "span#" + id + "_INPUT";
			var selDivId = "span#" + id + "_SELECT";
			
			//console.log(id);
			//console.log(inpDivId);
			//console.log(selDivId);
			$(inpDivId).hide();
			$(selDivId).show();
			
		} else {
			var id = $(this).attr('id');
			var inpDivId = "span#" + id + "_INPUT";
			var selDivId = "span#" + id + "_SELECT";
			
			$(inpDivId).show();
			$(selDivId).hide();
		}
	});
	
});

$(document).ready(function() {
	$('form').submit(function(e) {

		var flag = 0;
		console.log("submit");
		$(':text').each(function() {

			if ($(this).is(':visible')) {
				var val = $.trim($(this).val());

				if (!val) {
					flag = 1;
				}
			}
		})

		if (flag == 1) {
			alert("Some username / password fields are empty. Please fill out all fields ");
			e.preventDefault(e);
		}

	})
});
