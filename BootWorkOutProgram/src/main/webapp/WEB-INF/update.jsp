<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link href="/CSS/style.css" rel="stylesheet" type="text/css">
    <title>Update</title>
  </head>
  <body>
	  <header>
	  	Update an Exercise
	  	
	  </header>
   	  <nav>
		  	<ul>
		  		<li><a href="/.do">Home</a></li>
		  		<li><a href="create.do">Create</a></li>
		  		<li id="navUpdate" class="active">Update</li>
		  	
		  	</ul>
	  </nav>

	<div class="container" id="update">
		<form action="update.do" method="post">
			<div>
				<label for="id">Exercise ID: ${exer.id }</label>
				<input type="hidden" id="id" name="id" value="${exer.id}"/>
			</div>
			<div>
				<label for="name">Name: </label>
				<input id="name" name="name" type="text" maxlength="75" value="${exer.name}" required />
			</div>
			<div>
				<label for="category">Category: </label>
				<input id="category" name="category" maxlength="75" type="text" value="${exer.category}" required />
			</div>
			<div>
				<label for="description">Description: </label>
			</div>
			<div>	
				<textarea id="description" name="description" required maxlength="250" cols="35" rows="3">${exer.description}</textarea>
			</div>
			<div>
				<label for="sets">Sets: </label>
				<input id="sets" name="sets" type="number" value="${exer.sets}" required min="1" />
			</div>
			<div>
				<label for="reps">Reps: </label>
				<input id="reps" name="reps" type="number" value="${exer.reps}" required min="1" />
			</div>
			<div>
				<label for="rest">Rest (in seconds): </label>
				<input id="rest" name="rest" type="number" value="${exer.rest}" required min="0" />
			</div>
			<input type="submit" class="btn btn-primary" value="Update"/>
		</form>
	
	</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>