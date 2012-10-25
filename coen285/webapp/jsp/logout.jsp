<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="submitURL" value="/view/showlogin" />
<form:form modelAttribute="userDetail" action="${submitURL}" method="get" id="logout" name="logout">
	<br />
	<p>
		Hello <span class="subheading">
		<c:out value="${userDetail.firstName} ${userDetail.middleName} ${userDetail.lastName}"></c:out>		
		</span>
		<br /> <br /> Welcome to <span class="subheading">Schedu-elp</span>, your Academic Planner!
	</p>
	<br/>
	<hr/>
	<br/>
	<input type="submit" value="Sign Out" id="logoutButton" name="logoutButton" />
</form:form>
<script>
	var lbl = '<c:out value="${userDetail.firstName} ${userDetail.middleName} ${userDetail.lastName}"/>';  
	$("#signin").button("option", "label", lbl);
</script>

