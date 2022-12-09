<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Book Archive</title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="/static/css/app.css" rel="stylesheet"></link>
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
</head>

<body ng-app="myApp" ng-controller="listController as ctrl">

<div align="center" class="belch">
	
	<h2>List of Favorite Books</h2><BR>
    <form align="center" name="bookForm" ng-model="bookForm">
    <br><br><label>Title:  <input ng-model="title" type="text" name="title" required="required"/></label><br>
    	<label>Series:  <input ng-model="series" type="text" name="series"/></label><br>
        <label>Author:  <input ng-model="author" type="text" name="author" required="required"/></label><br>
        <label>Illustrator:  <input ng-model="illustrator" type="text" name="illustrator" /></label><br>
        <label>Genre:  <input ng-model="genre" type="text" name="genre" /></label><br>
        
        <br><br><input ng-if="!ctrl.book.id" type="submit" ng-click="submit()" value="Submit new book" />
		<input ng-if="ctrl.book.id" type="submit" ng-click="ctrl.update(book)" value="Update book" />

        
        <p style="color: red">${error}</p>
    </form><br>
</div>
<div align="center">
	
	<table border="1">
	<thead>
		<tr>
			<th>Book Id</th>
			<th>Title</th>
			<th>Series</th>
			<th>Author</th>
			<th>Illustrator</th>
			<th>Genre</th>
		</tr>
		</thead>
		<tbody>
		<div>
			<tr ng-repeat="book in books">
				<td>{{book.id}}</td>
				<td>{{book.title}}</td>
				<td>{{book.series}}</td>
				<td>{{book.author}}</td>
				<td>{{book.illustrator}}</td>
				<td>{{book.genre}}</td>
				<td>
					<button type="button" ng-click="ctrl.edit(book)" class="btn btn-success custom-width">Edit</button>
                    <button type="button" ng-click="ctrl.remove(book)" class="btn btn-danger custom-width">Remove</button>
                </td>
			</tr>
			</div>		
		</tbody>
	</table>
</div>
	  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="/static/js/service/listService.js"></script>
      <script src="/static/js/controller/listController.js"></script>

</body>
</html>