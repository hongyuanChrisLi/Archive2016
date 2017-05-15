$(document).ready(function() {
	$('[placeholder]').focus(function() {
		var input = $(this);
		if (input.val() == input.attr('placeholder')) {
			input.val('');
			input.removeClass('placeholder');
		}
	}).blur(function() {
		var input = $(this);
		if (input.val() == '' || input.val() == input.attr('placeholder')) {
			input.addClass('placeholder');
			input.val(input.attr('placeholder'));
		}
	}).blur().parents('form').submit(function() {
		$(this).find('[placeholder]').each(function() {
			var input = $(this);
			if (input.val() == input.attr('placeholder')) {
				input.val('');
			}
		})
	});
});

/*$(document).ready(function() {
	$('form').submit(function(e) {

		var flag = 0;
		console.log("submit");
		$(':input').each(function() {

			if ($(this).is(':text') || $(this).is(':password')) {
				var val = $.trim($(this).val());

				if (!val) {
					flag = 1;
				}
			}
		})

		if (flag == 1) {
			alert("Some fields are empty. Please fill out all fields ");
			e.preventDefault(e);
		}

	})
});
*/