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
		    <div>
				<h3>Here are some of the events in your state</h3>  
		    </div>
		    <div>
				<h3>Here are some of the events in other states</h3>  
		    </div>
   		    <div>
				<form:form action="/event/new" method="POST" modelAttribute="event">
					<p>
						<form:errors path="event.*"></form:errors>
					</p>
					<p>
						<form:label path="user">User</form:label>
						<form:errors path="user"/>
						<form:input path="user" value="${user.id}"/>
					</p>
					<p>
						<form:label path="name">Name</form:label>
						<form:errors path="name"/>
						<form:input path="name"/>
					</p>
					<p>
						<form:label path="eventDate"> Event Date</form:label>
						<form:errors path="eventDate"/>
						<form:input path="eventDate" />
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