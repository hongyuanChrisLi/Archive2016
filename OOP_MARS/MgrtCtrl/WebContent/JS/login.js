$(document).ready(function() {
	$('form').submit(function(e) {

		var flag = 0;
		console.log("submit");
		$('input[type=text], input[type=password]').each(function() {
			
			var val = $.trim($(this).val());
			if (!val) {
				flag = 1;
			}
		})

		if (flag == 1) {
			alert("Empty username or pasword. Please fill out all fields ");
			e.preventDefault(e);
		}

	})
});