<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Events</title>
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	</head>
	<body>
	    <div class="container">
		    <div>
				<h1>Welcome, <c:out value="${user.email}" /></h1>
				<a href="/logout">Logout</a>
		    </div>
		    <hr />
			<div>
				<p>
				<h5>Here are some of the events in your state</h5>  
				<table class="table">
				    <thead class="thead-dark">
				        <tr>
				            <th scope="col">Name</th>
				         	<th scope="col">Date</th>
				            <th scope="col">City</th>
				            <th scope="col">Host</th>
				            <th scope="col">Action</th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach items="${localEvents}" var="lev">
					        <tr>
					            <td><c:out value="${lev.name}"/></td>
					            <td><c:out value="${lev.eventDate}"/></td>
					            <td><c:out value="${lev.city}"/></td>
 				            	<td><c:out value="${lev.user.firstName}"/></td>
			            		<td>
			            			<c:choose>
			            				<c:when test="${lev.user.id == user.id}">
			            					<a href="/event/${lev.id}/destroy">delete</a> | 
			            					<a href="/event/${lev.id}/edit">edit</a>
		            					</c:when>
		            					<c:otherwise>
<%-- 											Create another choose statement that passes the user obect.  If the user object is found in the attendees list then let this person cancel
											Otherwise allow them to join which creates the relationship
											
											
											<c:choose>
												c:when test="" test to see .cotains objectpass along a user object and if true (show cancel) otherwise show join --%>
											<a href="/event/${lev.id}/join">join</a> | 
			            					<a href="/event/${lev.id}/cancel">cancel</a>
		            					</c:otherwise>
	            					</c:choose>
		            			</td>
					        </tr>
				        </c:forEach>
				    </tbody>
				</table>
			</div>
			<div>
				<p>
				<h5>Here are some of the events in other states</h5>  
				<table class="table">
				    <thead class="thead-dark">
				        <tr>
				            <th scope="col">Name</th>
				         	<th scope="col">Date</th>
				            <th scope="col">City</th>
				            <th scope="col">State</th>				            
				            <th scope="col">Host</th>
				            <th scope="col">Action</th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach items="${remoteEvents}" var="rev">
					        <tr>
					            <td><c:out value="${rev.name}"/></td>
					            <td><c:out value="${rev.eventDate}"/></td>
					            <td><c:out value="${rev.city}"/></td>
					            <td><c:out value="${rev.state}"/></td>
 				            	<td><c:out value="${rev.user.firstName}"/></td>
			            		<td>
			            			<a href="/event/${lev.id}/join">join</a> | 
			            			<a href="/event/${lev.id}/cancel">cancel</a>
		            			</td>
					        </tr>
				        </c:forEach>
				    </tbody>
				</table>
			</div>
			<hr />
   		    <div>
				<form:form action="/event/new" method="POST" modelAttribute="event">
					<p>
						<form:errors path="event.*"></form:errors>
					</p>
					<p>
<%-- 						<form:label path="user">User</form:label>
						<form:errors path="user"/> --%>
						<form:input path="user" value="${user.id}" type="hidden"/>
					</p>
					<p>
						<form:label path="name">Name</form:label>
						<form:errors path="name"/>
						<form:input path="name"/>
					</p>
					<p>
						<form:label path="eventDate"> Event Date</form:label>
						<form:errors path="eventDate"/>
						<form:input path="eventDate" type="date"/>
					</p>
					<p>
						<form:label path="city">City</form:label> 
						<form:errors path="city"/>
						<form:input path="city"/>
					</p>
					<p>
						<form:label path="state">State</form:label> 
						<form:errors path="state"/>
						<form:input path="state"/>
					</p>
					<input type="submit" value="Submit"/>
				</form:form>
		    </div>
	    </div>
	</body>
</html>