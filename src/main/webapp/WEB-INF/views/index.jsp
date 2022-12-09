<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Archive</title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body class="text-center">
	<form action="login" method="POST">
		<div align="center">
			<h2>Book Archive</h2><br>
			<div class="belch">
			<p>User Name:</p>
			<input type="text" name="username"><br>
			<p>Password:</p>
			<input type="password" name ="password"><br><br>
			<input type="submit">
			<p style="color: red">${error}</p>
			</div>
		</div>
	</form>		
</body>
</html>