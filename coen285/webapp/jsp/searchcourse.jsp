<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="searchURL" value="/view/search/result" />
<form:form modelAttribute="courseSearchTO" action="${searchURL}" method="post" id="srchCourseForm"
	name="srchCourseForm">
	<div id="leftDiv" class="ui-widget-content ui-corner-all ui-helper-reset">
		<p id="srchCourseError" class="ui-state-error ui-corner-all">
			<span class="ui-icon ui-icon-alert"></span> <span>Enter at least one criteria to find courses</span>
		</p>
		<table border="0" summary="search" width="100%" cellspacing="10px">
			<tr>
				<td class="subheading">Course Code</td>
			</tr>
			<tr>
				<td><form:input path="courseCode" id="courseCode" size="35" maxlength="35" /></td>
			</tr>
			<tr>
				<td class="subheading">Course Name</td>
			</tr>
			<tr>
				<td><form:input path="courseName" id="courseName" size="50" maxlength="50" /></td>
			</tr>
			<tr>
				<td class="subheading">Offered On</td>
			</tr>
			<tr>
				<td><form:checkboxes class="dayOfWeek" path="selectedDays" items="${daysOfWeek}" />
				</td>
			</tr>
			<tr>
				<td class="subheading">Course Timings</td>
			</tr>
			<tr>
				<td>Between&nbsp;&nbsp;<form:input class="timePicker" path="startTime" id="startTime"
						size="8" maxlength="10" /> &nbsp;&nbsp;and&nbsp;&nbsp;<form:input class="timePicker"
						path="endTime" id="endTime" size="8" maxlength="10" /></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="center">
					<button id="srchButton" type="submit" name="srchButton">
						<span class="ui-icon ui-icon-search"></span>Search
					</button></td>
			</tr>
		</table>
	</div>
	<c:if test="${!empty courseList}">
		<div id="rightDiv" class="ui-widget-content ui-corner-all ui-helper-reset">
			<table border="0" summary="result" width="100%" cellspacing="10px">
				<tr>
					<td colspan="5"><span class="tiny">* Course Timings are tentative and are likely to
							change each quarter</span></td>
				</tr>
				<c:forEach items="${courseList}" var="rs">
					<tr>
						<td width="5%">
							<button class="addBtn" name="addBtn${rs.courseCode}" id="addBtn${rs.courseCode}">
								<span class="ui-icon ui-icon-plusthick"></span>
							</button>
						</td>
						<td width="10%">${rs.courseCode}</td>
						<td width="35%">${rs.courseName}&nbsp;(${rs.units} units)<br /> 
							<span class="subheading">PREREQS: </span> 
							<c:forEach items="${rs.prerequisites}" var="pre">
								${pre}&nbsp;&nbsp;
							</c:forEach></td>
						<td width="30%">
							<c:forEach items="${rs.schedules}" var="sch">
								<span class="subheading">${sch.quarter}:&nbsp;</span>
								${sch.dayOfWeek}&nbsp;${sch.startTime}-${sch.endTime}<br />
							</c:forEach>
						</td>
						<td width="20%">
							<button class="reviewBtn" name="reviewBtn${rs.courseCode}"
								id="reviewBtn${rs.courseCode}">Read Reviews</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
</form:form>
