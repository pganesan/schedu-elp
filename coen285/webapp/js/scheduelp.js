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

	// searchrestaurant.jsp
	$("#srchRestForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateSrchRest($("#srchRestError"));
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
	// make new reservation
	$(".reserveBtn").live("click", function() {
		var restID = $(this).attr("id").substring(10);
		$.ajax({
			data : {
				rest : restID,
			},
			type : 'GET',
			url : '/scheduelp/view/reserve/new',
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
				$("#reserveDate").datepicker({
					showButtonPanel : true,
					minDate : new Date(),
					maxDate : new Date().getDate + 6
				});
				$("#reserveTime").timepicker({
					ampm : true,
					hourMin : 10,
					hourMax : 22
				});
			},
			error : function(xhr, status, error) {
				$(".scheduelpInfo").html(xhr.responseText);
				$("#srchRestError").hide();
				$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Make a Reservation");
				$(".scheduelpInfo").dialog("open");
			}
		});

		// prevent default submit
		return false;
	});
	// submit new reservation
	$("#reserveForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateReserve();
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				$(".scheduelpInfo").html(response);
				$("#reserveError").hide();
				$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Reservation Confirmation");
				$(".scheduelpInfo").dialog("open");
			},
			error : function(xhr, status, error) {
				showError($("#reserveError"), xhr.responseText);
			}
		});

		// prevent default submit
		return false;
	});
	// edit/cancel current reservation
	$(".reserveLink").live("click", function() {
		var rsvNo = $(this).html().substring(13);
		$.ajax({
			data : {
				rsvNo : rsvNo,
			},
			type : $("#myreserveForm").attr("method"),
			url : $("#myreserveForm").attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
			}	
		});

		// prevent default submit
		return false;
	});	
	// submit edit reservation
	$("#editRsvBtn").live("click", function() {
		if ($(this).find('span').html() == "Edit") {
			$('#viewRsvForm input:text').removeAttr('disabled');
			$('#viewRsvForm select').removeAttr('disabled');
			$(this).find('span').html("Update");
			$("#viewRsvForm #rsvDate").datepicker({
				showButtonPanel : true,
				minDate : new Date(),
				maxDate : new Date().getDate + 6
			});
			$("#viewRsvForm #rsvTime").timepicker({
				ampm : true,
				hourMin : 10,
				hourMax : 22
			});			
		} else {
			$.ajax({
				beforeSend : function() {
					// if this returns false, the ajax call will not be made
					return validateRsvEdit();
				},				
				data : $('#viewRsvForm').serialize(),
				type : $('#viewRsvForm').attr("method"),
				url : $('#viewRsvForm').attr("action"),
				success : function(response) {
					// update the tab content
					$(".scheduelpInfo").html(response);
					$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Update Reservation");
					$(".scheduelpInfo").dialog("open");
					$("#menu").tabs('load', 3);
				}	
			});			
		}
		
		// prevent default submit
		return false;
	});		
	// submit cancel reservation
	$("#viewRsvForm").live("submit", function() {
		$.ajax({
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : '/scheduelp/view/reserve/cancel',
			success : function(response) {
				// update the tab content
				$(".scheduelpInfo").html(response);
				$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Cancel Reservation");
				$(".scheduelpInfo").dialog("open");
				$("#menu").tabs('load', 3);
			}	
		});		
		
		// prevent default submit
		return false;
	});		
	// back to view all reservations
	$("#backBtn").live("click", function() {
		$("#menu").tabs('load', 3);
		
		// prevent default submit
		return false;
	});	
	// searchreview.jsp
	$("#srchReviewForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateReviewRest($("#srchReviewError"));
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
				$("#sortradio").buttonset();
				$("#filterradio").buttonset();
			}
		});

		// prevent default submit
		return false;
	});
	// sort/filter reviews
	$(".sortradio, .filterradio").live("click", function() {
		// create an AJAX call
		$.ajax({
			data : $("#srchReviewForm").serialize(),
			type : $("#srchReviewForm").attr("method"),
			url : '/scheduelp/view/review/filtersort',
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
				$("#sortradio").buttonset();
				$("#filterradio").buttonset();

			}
		});

		// prevent default submit
		return false;
	});
	// show all reviews for selected restaurant
	$(".reviewBtn").live("click", function() {
		var restID = $(this).attr("id").substring(9);
		$.ajax({
			data : {
				rest : restID,
			},
			type : 'GET',
			url : '/scheduelp/view/review/viewall',
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
		var restID = $("#viewReviewForm #restaurantId").val();
		$.ajax({
			data : {
				rest : restID,
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
	// searchtogo.jsp
	$("#srchTogoForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateSrchTogo($("#srchTogoError"));
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
	// view menu for selected restaurant
	$(".togoButton").live("click", function() {
		var restID = $(this).attr("id").substring(10);
		$.ajax({
			data : {
				rest : restID,
			},
			type : 'POST',
			url : '/scheduelp/view/togo/menu',
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
				$(".addItem").button();
			},
			error : function(xhr, status, error) {
				$(".scheduelpInfo").html(xhr.responseText);
				$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Order a Takeout");
				$(".scheduelpInfo").dialog("open");
			}
		});

		// prevent default submit
		return false;
	});	
	// add items to togo order from menu
	$(".addItem").live("click", function() {
		var btnText = $(this).siblings("label").find('.ui-button-text');
		if(btnText.text() == "Add Item") {
			btnText.text("Remove Item");
		} else {
			btnText.text("Add Item");
		}
		return true;
	});
	// complete order from menu page
	$("#viewmenuForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateTogoOrder();
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
				$("#orderError").hide();
			}
		});

		// prevent default submit
		return false;
	});
	// change quantity of items in order page
	$(".qtyTD").live("change", function() {
		var itemPrice = $(this).next("input:hidden").val();
		var price = ($(this).val() * itemPrice).toFixed(2);
		$(this).parent().prev(".priceTD").html("&#36;" + price);
		
		var subtotal = 0.0, tax = 0.0, total = 0.0;
		$(".priceTD").each(function(index) {
			subtotal += parseFloat($(this).html().substring(1));
		});		
		subtotal = parseFloat(subtotal).toFixed(2);
		tax = parseFloat(0.085 * subtotal).toFixed(2);	
		total = parseFloat(parseFloat(subtotal) + parseFloat(tax)).toFixed(2);
		
		$("#subtotal").html("&#36;" + subtotal);
		$("#tax").html("&#36;" + tax);
		$("#total").val(total);

		return true;
	});	
	// confirm order in order page
	$("#orderForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateConfirmOrder();
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// update the tab content
				$(".scheduelpInfo").html(response);
				$(".scheduelpInfo").dialog("option", "title", "Schedu-elp - Takeout Order Confirmation");
				$(".scheduelpInfo").dialog("open");
				$("#menu").tabs('load', 2);
			}
		});

		// prevent default submit
		return false;
	});	
	// cancel order in order page
	$("#orderCancelBtn").live("click", function() {
		$("#menu").tabs('load', 2);
		
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
	$("input:submit, input:button, button").button();
	$("#loginbox").hide();
	$("#ui-tabs-4 form > div").accordion({
		collapsible : true,
		autoHeight : false
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
function validateSrchRest(errorObj) {
	if (isEmpty($("#srchRestForm #restName")) && checkValue($("#srchRestForm #cuisine"), "0") && isEmpty($("#srchRestForm #near"))) {
		showError(errorObj, "Please enter a search criteria to find restaurants");
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

