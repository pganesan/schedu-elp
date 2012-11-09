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
				<td class="subheading">${reviewTo.courseCode}&nbsp;-&nbsp;${reviewTo.courseName} <form:hidden
						path="courseCode" id="courseId" />
				</td>
			</tr>
			<tr>
				<td>${reviewTo.courseDesc}<br />
				<c:choose>
					<c:when test="${fn:startsWith(reviewTo.courseCode,'COEN')}">
						<a class="tiny" href="http://www.scu.edu/academics/bulletins/engineering/coengrad.cfm" target="_blank">Read more...</a>
					</c:when> 
					<c:when test="${fn:contains(reviewTo.courseCode,'MGT')}">
						<a class="tiny" href="http://www.scu.edu/academics/bulletins/engineering/emgt.cfm" target="_blank">Read more...</a>
					</c:when> 
					<c:when test="${fn:startsWith(reviewTo.courseCode,'ENGR')}">
						<a class="tiny" href="http://www.scu.edu/academics/bulletins/engineering/engr.cfm"
							target="_blank">Read more...</a>
					</c:when> 
					<c:when test="${fn:startsWith(reviewTo.courseCode,'AMTH')}">
						<a class="tiny" href="http://www.scu.edu/academics/bulletins/engineering/amth.cfm"
							target="_blank">Read more...</a>
					</c:when> 					
					<c:otherwise>
						<a class="tiny" href="http://www.scu.edu/academics/bulletins/engineering/" target="_blank">Read
							more...</a>
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td><span class="subheading">CREDIT: </span>${reviewTo.units}&nbsp;units</td>
			</tr>
			<tr>
				<td><span class="subheading">PREREQS: </span><br /> <c:forEach
						items="${reviewTo.prerequisites}" var="pre">
						${pre}&nbsp;&nbsp;
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td><span class="subheading">COURSE TIMINGS: </span><br /> <c:forEach
						items="${reviewTo.schedules}" var="sch">
						${sch.quarter}:&nbsp;${sch.dayOfWeek}&nbsp;${sch.startTime}-${sch.endTime}<br />
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="center">
					<button id="newRvBtn" type="submit" name="newRvBtn">
						<span class="ui-icon ui-icon-comment"></span>Write a review
					</button>
				</td>
			</tr>
		</table>
	</div>
	<c:if test="${!empty reviewTo.reviews}">
		<div id="rightDiv" class="ui-widget-content ui-corner-all ui-helper-reset">
			<table border="0" summary="review" width="100%" cellspacing="10px">
				<c:forEach items="${reviewTo.reviews}" var="rv">
					<tr>
						<td><c:forEach begin="1" end="${rv.rating}">
								<img src='../images/star.png' alt='star' />&nbsp;
						</c:forEach> Reviewed on <span class='subheading'>${rv.reviewDateTime}</span> by <span class='subheading'>${rv.student}</span>
							<br /> <br /> ${rv.comments}</td>
					</tr>
					<tr>
						<td><hr /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
</form:form>
