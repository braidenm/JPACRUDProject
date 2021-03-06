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
<title>SQL CRUD</title>
</head>
<body>

	<header> 
			<nav class="navbar navbar-inverse navbar-fixed-top">
			  <div class="container-fluid">
			   
			    <ul class="nav navbar-nav">
			      <li><a href="home.do">Home</a></li>
			      <li class="dropdown active"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Plans<span class="caret"></span></a>
			        <ul class="dropdown-menu">
			          <li><a href="plan.do">Plans</a></li>
			          <li><a href="createPlan.do">Create Plan</a></li>
			        </ul>
			      </li>
			      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Days<span class="caret"></span></a>
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
	
	
		Work-out Plan
	 </header>

	<div class="container">


		<div class="row">
			<div class="col-sm-6" id="left">
				<h3>Find an Plan:</h3>
				<div>
					<form action="getPlan.do" method="post" id="number">
						<label for="id">Index Number: </label> 
						<input type="number" name="id" required min="1" /> 
						<input type="submit" class="btn btn-primary" value="Get Plan" />
					</form>
				</div>
				<div>
					<form action="searchPlan.do" method="post" id="search">
						<label for="search">Search Word(s): </label> 
						<input type="text" name="search" required /> 
						<input type="submit" class="btn btn-primary" value="Get Plan(s)" />
					</form>
				</div>
				<div>
					<form action="getallPlan.do" method="post" id="all">
						<input type="submit" class="btn btn-primary" value="Get All Plans" />
					</form>
				</div>
				<div></div>

			</div>
			<div class="col-sm-6">
				<c:if test="${not empty plan}">
					<div class="info">
						<strong>Plan ID:</Strong> ${plan.id} 
						<br> 
						<strong>Name: </strong>${plan.name} 
						<div class="nav" id="bottomNav">
							<div class="dropdown">
								<button class="btn btn-primary dropdown-toggle" type="button"
									data-toggle="dropdown">Days: <span class="caret"></span>
								</button>
							<ul class="dropdown-menu ">
									<c:forEach items="${plan.days}" var="day">
										<li><Strong>Day id: </Strong>${day.id}</li>
										<li><Strong>Day Name: </Strong>${day.name}</li>
										<li class="divider"></li>
								
									</c:forEach>
							</ul>
						</div>
	
								<div>
									<form action="updatePlan.do" method="get">
										<input name="id" value="${plan.id}" type="hidden" /> 
										<input type="submit" class="btn btn-primary" value="Edit Plan" />
									</form>
								</div>
								<div>
									<form action="destroyPlan.do" method="post">
										<input name="delete" value="${plan.id }" type="hidden" /> 
										<input type="submit" class="btn btn-danger" value="Delete Plan"/>
									</form>
	
								</div>

						</div>
					</div>
				</c:if>
				<c:if test="${not empty planList }">
					<c:forEach var="plan" items="${planList }">
						<div class="info">

							<strong>Plan ID:</strong> ${plan.id}
							<br>
							<strong>Name:</strong>${plan.name} 
							<div class="nav" id="bottomNav">
								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle" type="button"
										data-toggle="dropdown">Days: <span class="caret"></span>
									</button>
									<ul class="dropdown-menu ">
										<c:forEach items="${plan.days}" var="day">
											<li><Strong>Day id: </Strong>${day.id}</li>
											<li><Strong>Day Name: </Strong>${day.name}</li>
											<li class="divider"></li>
									
										</c:forEach>
									</ul>
								</div>
								

								<div>
									<form action="updatePlan.do" method="get">
										<input name="id" value="${plan.id}" type="hidden" /> 
										<input type="submit" class="btn btn-primary" value="Edit Plan" />
									</form>
								</div>
								<div>
									<form action="destroyPlan.do" method="post">
										<input name="delete" value="${plan.id }" type="hidden" /> 
										<input type="submit" class="btn btn-danger" value="Delete Plan" />
									</form>
								</div>

							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${deleted }">
					<div class="message">Plan ${planId} Deleted</div>
				</c:if>
				<c:if test="${notFound }">
					<div class="message">No Plans Were Found</div>

				</c:if>

			</div>
		</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>