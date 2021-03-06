<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Registration & Login</title>
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	</head>
	<body>
	    <div class="container">
		    <div>
			    <h1>Register!</h1>
			    
			    <p><form:errors path="user.*"/></p>
			    
			    <form:form method="POST" action="/registration" modelAttribute="user">
			        <p>
			            <form:label path="firstName">First Name:</form:label>
			            <form:input type="firstName" path="firstName"/>
			        </p>
			        <p>
			            <form:label path="lastName">Last Name:</form:label>
			            <form:input type="lastName" path="lastName"/>
			        </p>
			        <p>
			            <form:label path="email">Email:</form:label>
			            <form:input type="email" path="email"/>
			            <form:errors path="duplicate" />
			        </p>
			        <p>
			            <form:label path="city">City:</form:label>
			            <form:input type="city" path="city"/>
			        </p>
			        <p>
			            <form:label path="state">State:</form:label>
			            <form:input type="state" path="state"/>
			        </p>
			        <p>
			            <form:label path="password">Password:</form:label>
			            <form:password path="password"/>
			        </p>
			        <p>
			            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
			            <form:password path="passwordConfirmation"/>
			        </p>
			        <input type="submit" value="Register!"/>
			    </form:form>
		    </div>
		    <hr />
		    <div>
		    	<h1>Login</h1>
	    		<p><c:out value="${error}" /></p>
	    		<form method="post" action="/login">
	        		<p>
	            		<label for="email">Email</label>
	            		<input type="text" id="email" name="email"/>
	       			</p>
	        		<p>
	            		<label for="password">Password</label>
	            		<input type="password" id="password" name="password"/>
	        		</p>
	        		<input type="submit" value="Login!"/>
	    		</form>   
		    </div>
	    </div>
	</body>
</html>