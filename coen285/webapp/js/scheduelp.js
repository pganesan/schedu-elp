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

	// search course
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

	// write new review
	$("#viewReviewForm").live("submit", function() {
		var cid = $("#viewReviewForm #courseId").val();
		$.ajax({
			data : {
				cid : cid,
			},
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// new review dialog
				$("#newReviewDialog").html(response);
				$("#newReviewDialog").dialog("open");
			},
			error : function(xhr, status, error) {
				$(".scheduelpInfo").html(xhr.responseText);
				$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Write a Review");
				$(".scheduelpInfo").dialog("open");
			}
		});

		// prevent default submit
		return false;
	});

	// add or remove selected course
	$(".addBtn").live("click", function() {
		var courseCode = $(this).attr("id").substring(6);
		var urrl = '/scheduelp/view/pos/remove';

		var btnIcon = $(this).find('.ui-icon');
		if (btnIcon.hasClass('ui-icon-plusthick')) {
			urrl = '/scheduelp/view/pos/add';
		}
		$.ajax({
			data : {
				cid : courseCode,
			},
			type : 'POST',
			url : urrl,
			success : function(response) {
				btnIcon.toggleClass('ui-icon-plusthick ui-icon-minusthick')
				// display success message
				$(".scheduelpInfo").html(response);
				$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Program of Studies");
				$(".scheduelpInfo").dialog("open");
			},
			error : function(xhr, status, error) {
				$(".scheduelpInfo").html(xhr.responseText);
				$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Program of Studies");
				$(".scheduelpInfo").dialog("open");
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
		showMinute : false
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

// validate course search
function validateSrchCourse(errorObj) {
	var checked = $('#srchCourseForm input[type="checkbox"]').is(':checked');
	if ((!checked) && (isEmpty($("#srchCourseForm #startTime")) || isEmpty($("#srchCourseForm #endTime"))) && isEmpty($("#srchCourseForm #courseCode")) && isEmpty($("#srchCourseForm #courseName"))) {
		showError(errorObj, "Please enter a search criteria to search for courses");
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
	if (isEmpty($("#newReviewForm #comments"))) {
		showError($("#newReviewError"), "Please enter review comments");
		return false;
	}
}
