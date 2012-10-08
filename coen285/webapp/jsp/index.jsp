<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Schedu-elp: Your Academic Planning System</title>
		
		<link rel="stylesheet" type="text/css" href="../jquery_lib/jquery-ui.css"/>
		<link rel="stylesheet" type="text/css" href="../jquery_lib/jquery-ui-timepicker.css"/>		
		<link rel="stylesheet" type="text/css" href="../css/scheduelp.css"/>
		
		<script src="../jquery_lib/jquery.js" type="text/javascript"></script>
		<script src="../jquery_lib/jquery-ui.js" type="text/javascript"></script>
		<script src="../jquery_lib/jquery-ui-timepicker.js" type="text/javascript"></script>
		<script src="../js/scheduelp.js" type="text/javascript"></script>
		<script src="../js/utility.js" type="text/javascript"></script>
	</head>
	<c:url var="home" value="/view/home" />
	<c:url var="makeReserve" value="/view/reserve/search" />
	<c:url var="myReserve" value="/view/reserve/viewall" />
	<c:url var="restReview" value="/view/review/search" />
	<c:url var="orderTogo" value="/view/togo/search" />
	<body>
		<div id="header">
			<table summary="header" width="100%">
				<tr>
					<td width="20%"><img id=logo alt="Site logo" src="../images/logo.jpg" /></td>
					<td class="title">Schedu-elp</td>
					<td width="20%">
					<button id="signin">
						Sign in
					</button></td>
				</tr>
			</table>
		</div>
		<div id="loginbox" class="ui-widget-content ui-corner-all"></div>
		<div id="registerDialog" title="Member Registration"></div>
		<div id="newReviewDialog" title="Write a Review"></div>
		<div class="scheduelpInfo" title=""></div>
		<div id="content">
			<div id="menu">
				<ul>
					<li>
						<a href="${home}">Home</a>
					</li>
					<li>
						<a href="${makeReserve}">Course Search</a>
					</li>
					<li>
						<a href="${myReserve}">Course Reviews</a>
					</li>					
					<li>
						<a href="${orderTogo}">My Program of Study</a>
					</li>	
					<li>
						<a href="${restReview}">Help</a>
					</li>					
				</ul>
			</div>
		</div>
		<div id="footer">
			<p id="copyright">
				&copy; The content of these web pages is not generated by and does not represent the views of Santa Clara University or
				any of its departments or organizations.
			</p>
		</div>
	</body>
</html>
