<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="CSS/style.css" rel="stylesheet" type="text/css">
    <title>Update</title>
  </head>
  <body>
	  <header>
	  		<nav class="navbar navbar-inverse navbar-fixed-top">
			  <div class="container-fluid">
			   
			    <ul class="nav navbar-nav">
			      <li class="active"><a href="home.do">Home</a></li>
			      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Plans<span class="caret"></span></a>
			        <ul class="dropdown-menu">
			          <li><a href="plan.do">Plans</a></li>
			          <li><a href="createPlan.do">Create Plan</a></li>
			        </ul>
			      </li>
			      <li class="dropdown active"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Days<span class="caret"></span></a>
			        <ul class="dropdown-menu">
			          <li><a href="day.do">Days</a></li>
			          <li><a href="createDay.do">Create Day</a></li>
			        </ul>
			      </li>
			      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Exercises<span class="caret"></span></a>
			        <ul class="dropdown-menu">
			          <li><a href="exercises.do">Exercises</a></li>
			          <li><a href="createExercise.do">Create Exercise</a></li>
			        </ul>
			      </li>
			    </ul>
			  </div>
			</nav>
	
	  		
	  	Update a Work-out Day
	  	
	  </header>
   	 

	<div class="container" id="update">
		<form action="updateDay.do" method="post">
			<div>
				<label for="id">Day ID: ${day.id }</label>
				<input type="hidden" id="id" name="id" value="${day.id}"/>
			</div>
			<div>
				<label for="name">Name: </label>
				<input id="name" name="name" type="text" maxlength="75" value="${day.name}" required />
			</div>
			<div>
				<label for="day">Day of Week: </label>
				<input id="day" name="day" type="text" maxlength="75" value="${day.day}" required />
			</div>
			<div>
				<label for="plans"><strong>Plans: </strong></label>
				<br>
				<c:forEach var="plan" items="${day.plans}">
					<input id="plans" name="plans" type="checkbox" value="${plan.id}" checked>
					<strong>Plan id: </strong>${plan.id} <strong>Name: </strong>${plan.name}<br>
				</c:forEach>
				<c:forEach var="plan" items="${remainingPlans}">
					<input id="plans" name="plans" type="checkbox" value="${plan.id}">
					<strong>Plan id: </strong>${plan.id} <strong>Name: </strong>${plan.name}<br>
				</c:forEach>
			</div>
			<div>
			<br>
				<label for="exercises"><strong>Exercises: </strong></label>
				<br>
				<c:forEach var="exer" items="${day.exercises}">
					<input id="exercises" name="exercises" type="checkbox" value="${exer.id}" checked>
					<strong>Exercise id: </strong>${exer.id} <strong>Name: </strong>${exer.name}<br>
				</c:forEach>
				<c:forEach var="exer" items="${remainingExercises}">
					<input id="exercises" name="exercises" type="checkbox" value="${exer.id}">
					<strong>Exercise id: </strong>${exer.id} <strong>Name: </strong>${exer.name}<br>
				</c:forEach>
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