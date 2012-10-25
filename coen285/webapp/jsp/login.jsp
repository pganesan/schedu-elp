<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<c:url var="submitURL" value="/view/login" />
<form:form modelAttribute="userTo" action="${submitURL}" method="post" id="login" name="login">
		<p id="loginError" class="ui-state-error ui-corner-all">
			<span class="ui-icon ui-icon-alert"></span>
			<span></span>
		</p>
	<label for="userName">eCampus ID</label>
	<br />
	<form:input id="userID" path="userID" size="20" maxlength="20"/>	
	<br />
	<br />
	<label for="userPwd">Password</label>
	<br />
	<form:password id="userPwd" path="userPwd" size="20" maxlength="20" />
	<br />
	<br />
	<input type="submit" value="Sign in" id="loginButton" name="loginButton" />
</form:form>
