<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${language.name}</title>
	</head>
	<body>
		<a href="/languages">Dashboard</a>
		<p>
		<h1><c:out value="${language.name}"/></h1>
		<p>Creator: <c:out value="${language.creator}"/></p>
		<p>Version: <c:out value="${language.version}"/></p>
		<p>Number of pages: <c:out value="${book.numberOfPages}"/></p>
		<a href="/languages/${language.id}/edit">Edit</a>
		<a href="/languages/${language.id}/destroy">delete</a>
	</body>
</html>