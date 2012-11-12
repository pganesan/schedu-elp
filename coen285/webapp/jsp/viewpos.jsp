<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="submitURL" value="/view/pos/print" />
<form:form modelAttribute="posTo" action="${submitURL}" method="get" id="viewPOSForm"
	name="viewPOSForm">
	<div id="leftDiv" class="ui-widget-content ui-corner-all ui-helper-reset">
		<h3 class="ui-widget-header ui-corner-all ui-helper-reset">Program Of Studies</h3>
		<table border="0" summary="search" width="100%" cellspacing="10px">
			<tr>
				<td><span class="subheading">NAME (Last, First, M.I.):&nbsp;</span>
				${userDetail.lastName},&nbsp;${userDetail.firstName},&nbsp;${userDetail.middleName}
				</td>
			</tr>
			<tr>
				<td><span class="subheading">STUDENT ID:&nbsp;</span>${userDetail.userID}
				</td>
			</tr>
			<tr>
				<td><span class="subheading">EMAIL ADDRESS:&nbsp;</span>${userDetail.email}
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span class="subheading">DEGREE PROGRAM:&nbsp;</span><br/>
				${userDetail.degreeDesc}&nbsp;(${userDetail.degree})
				</td>
			</tr>	
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span class="subheading">TOTAL NUMBER OF PLANNED UNITS:&nbsp;</span>${posTo.plannedUnits}</td>
			</tr>	
			<tr>
				<td><div id="progressbar"></div>&nbsp;<span class="subheading">${posTo.percentComplete}% Planning Complete</span></td>
			</tr>										
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="center">
					<button id="printBtn" type="submit" name="printBtn">
						<span class="ui-icon ui-icon-print"></span>Print
					</button>
				</td>
			</tr>
		</table>
	</div>
	<c:if test="${!empty posTo.programMap}">
		<div id="rightDiv">
			<c:forEach var="category" items="${posTo.programMap}">
				<h3>
					<a href="#">${category.key.requirementDesc}
					<c:if test="${category.key.reqtUnits != 0}">
						&nbsp;(${category.key.reqtUnits}&nbsp;units)
					</c:if>
					</a>
				</h3>
				<div>
					<table border="0" summary="courseList" width="95%" cellspacing="10px">
						<tr>
							<td class="subheading">COURSE NUMBER</td>
							<td class="subheading">COURSE TITLE</td>
							<td class="subheading">UNITS</td>
						</tr>
						<c:forEach items="${category.value}" var="course">
							<tr>
								<td width="25%">${course.courseCode}</td>
								<td width="50%">${course.courseName}</td>
								<td width="25%">${course.units}</td>
							</tr>							
						</c:forEach>
					</table>
				</div>
			</c:forEach>
		</div>
	</c:if>
</form:form>
<script>
	var percentComplete = '<c:out value="${posTo.percentComplete}"/>'; 
	$("#progressbar").progressbar({
    	value: parseInt(percentComplete)
	});

</script>
