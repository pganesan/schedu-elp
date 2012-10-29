$(function() {
	startup();

	$(".ui-tabs-panel:visible").ajaxSuccess(function() {
		init();
	});
	$("#ui-tabs-4").ajaxError(function(event, xhr, settings, thrownError) {
		$("#ui-tabs-4").html(xhr.responseText);
	});
	// login
	$("#login").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateLogin();
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				$("#loginbox").html(response);
			},
			error : function(xhr, status, error) {
				showError($("#loginError"), xhr.responseText);
			}
		});

		// prevent default submit
		return false;
	});
	// logout
	$("#logout").live("submit", function() {
		// create an AJAX call
		$.ajax({
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				$("#loginbox").html(response);
				$("#signin").button("option", "label", "Sign in");
			}
		});

		// prevent default submit
		return false;
	});

	// searchcourse.jsp
	$("#srchCourseForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateSrchCourse($("#srchCourseError"));
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
			}
		});

		// prevent default submit
		return false;
	});
	
	// show all reviews for selected course
	$(".reviewBtn").live("click", function() {
		var courseCode = $(this).attr("id").substring(9);
		$.ajax({
			data : {
				cid : courseCode,
			},
			type : 'GET',
			url : '/scheduelp/view/review/list',
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
			}	
		});

		// prevent default submit
		return false;
	});

});

// things to do on document load
function startup() {
	// create tabs
	$("#menu").tabs();

	// load sign in page
	$("#signin").button({
		icons : {
			secondary : "ui-icon-triangle-1-s"
		}
	});
	// show signin dialog on startup
	$("#loginbox").load("/scheduelp/view/showlogin");
	
	// submit signin
	$("#signin").click(function() {
		$("#loginbox").slideToggle("medium");
		$("#loginError").hide();
		$("#loginbox :input").removeClass("ui-state-error");
	});
	// information dialog used across app
	$(".scheduelpInfo").dialog({
		autoOpen : false,
		modal : true,
		height : 175,
		width : 500,
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});

	// new review dialog
	$("#newReviewDialog").dialog({
		autoOpen : false,
		height : 350,
		width : 600,
		modal : true,
		buttons : {
			"Post" : function() {
				// create an AJAX call
				$.ajax({
					// validate form before ajax call;
					beforeSend : function() {
						// if this returns false, the ajax call will not be made
						return validateNewReview();
					},
					data : $("#newReviewForm").serialize(),
					type : $("#newReviewForm").attr("method"),
					url : $("#newReviewForm").attr("action"),
					success : function(response) {
						// close popup and refresh parent tab
						$("#newReviewDialog").dialog("close");
						$(".ui-tabs-panel:visible").html(response);
					},
					error : function(xhr, status, error) {
						showError($("#newReviewError"), xhr.responseText);
					}
				});
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});			
}

// initialize all elements on the visible form using jquery ui theme
function init() {
	$("input:submit, input:button, button, input:checkbox").button();
	$("#loginbox").hide();
	$(".timePicker").timepicker({
		ampm : true,
		hourMin : 7,
		hourMax : 22,
		showMinute: false
	});	
}

// validate signin
function validateLogin() {
	// if this returns false, the ajax call will not be made
	if (isEmpty($("#userID"))) {
		showError($("#loginError"), "Please enter your eCampus User ID");
		return false;
	} else if (isEmpty($("#userPwd"))) {
		showError($("#loginError"), "Please enter your eCampus Password");
		return false;
	}
}

// validate restaurant search for reservation
function validateSrchCourse(errorObj) {
	var checked = $('#srchCourseForm input[type="checkbox"]').is(':checked');
	if ((!checked) && (isEmpty($("#srchCourseForm #startTime")) || isEmpty($("#srchCourseForm #endTime")))
		&& isEmpty($("#srchCourseForm #courseCode")) && isEmpty($("#srchCourseForm #courseName"))) {
		showError(errorObj, "Please enter a search criteria to search for courses");
		return false;
	}
}

// validate reservation form (new)
function validateReserve() {
	if (isEmpty($("#reserveDate"))) {
		showError($("#reserveError"), "Please pick a reservation date");
		return false;
	}
	if (isEmpty($("#reserveTime"))) {
		showError($("#reserveError"), "Please pick a reservation time");
		return false;
	}

	var resDateTime = new Date($("#reserveDate").val().concat(" ").concat($("#reserveTime").val()));
	var minDateTime = new Date().getTime() + (2 * 60 * 60 * 1000);
	if (resDateTime.getTime() < minDateTime) {
		showError($("#reserveError"), "Reservations can only be made starting 2 hours from now");
		return false;
	}
}

// validate reservation form (edit)
function validateRsvEdit() {
	if (isEmpty($("#rsvDate"))) {
		showError($("#rsvEditError"), "Please pick a reservation date");
		return false;
	}
	if (isEmpty($("#rsvTime"))) {
		showError($("#rsvEditError"), "Please pick a reservation time");
		return false;
	}

	var resDateTime = new Date($("#rsvDate").val().concat(" ").concat($("#rsvTime").val()));
	var minDateTime = new Date().getTime() + (2 * 60 * 60 * 1000);
	if (resDateTime.getTime() < minDateTime) {
		showError($("#rsvEditError"), "Reservations can only be made starting 2 hours from now");
		return false;
	}
}

// validate restaurant search for reviews
function validateReviewRest(errorObj) {
	if (isEmpty($("#srchReviewForm #restName")) && checkValue($("#srchReviewForm #cuisine"), "0") && isEmpty($("#srchReviewForm #near"))) {
		showError(errorObj, "Please enter a search criteria to find restaurants");
		return false;
	}
}

// validate new review form
function validateNewReview() {
	var checked = $('#newReviewForm input[type="radio"]').is(':checked');
	if (!checked) {
		showError($("#newReviewError"), "Please choose a rating");
		return false;
	}
}

// validate restaurant search for togo order
function validateSrchTogo(errorObj) {
	if (isEmpty($("#srchTogoForm #restName")) && checkValue($("#srchTogoForm #cuisine"), "0") && isEmpty($("#srchTogoForm #near"))) {
		showError(errorObj, "Please enter a search criteria to find restaurants");
		return false;
	}
}

// validate togo order form
function validateTogoOrder() {
	var checked = $('#viewmenuForm input[type="checkbox"]').is(':checked');
	if (!checked) {
		showError($("#viewmenuError"), "You must choose atleast one item to complete the order");
		return false;
	}
}

// validate togo order confirmation form
function validateConfirmOrder() {
	if (isEmpty($("#cNumber"))) {
		showError($("#orderError"), "Please enter credit card number to process your order");
		return false;
	}
	if (isEmpty($("#cName"))) {
		showError($("#orderError"), "Please enter credit card holder's name to process your order");
		return false;
	}
}

