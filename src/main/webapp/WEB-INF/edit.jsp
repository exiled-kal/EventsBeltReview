<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit Event</title>
	</head>
	<body>
		<h1>Edit Event</h1>
		<form:form action="/event/${event.id}" method="post" modelAttribute="event">
		    <input type="hidden" name="_method" value="put">
			<p>
				<form:input path="user" value="${user.id}" type="hidden"/>
			</p>
		    <p>
		        <form:label path="name">Name</form:label>
		        <form:errors path="name"/>
		        <form:input path="name"/>
		    </p>
		    <p>
		        <form:label path="eventDate">Event Date</form:label>
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
	</body>
</html>