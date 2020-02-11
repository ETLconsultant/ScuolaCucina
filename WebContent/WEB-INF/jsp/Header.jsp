<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="css/header.css">

<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-light" style="background-color: #8B0000;">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="Home.jsp">Javani Courses</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="#about">Chi siamo</a></li>
					 <li><form action="servlet/CatalogoCorsiServlet">
					<input type="submit" name="submit" value="Catalogo"
								class="button2">
					</form></li>
					<li><a href="calendario.jsp">Calendario</a></li>
				</ul> 

				<ul class="nav navbar-nav navbar-right">
		 			<%
						String username = (String) session.getAttribute("username");
					%> 
					<%
						if (session.getAttribute("username") == null) {
					%>
					<li><a href="login.jsp"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
					<%
						} else {  
					%>
					<li><a href="carrello.jsp"> Carrello</a></li>
					<li><a class="active" href="areaPersonale.jsp"> <%=username%></a></li>
					 <li><form action="LogoutUtenteServlet">
					<input type="submit" name="submit" value="Logout"
								class="button2">
					</form></li>
					

					<%
						}
					%>
				</ul>
			</div>
		</div>
	</nav>

</body>
</html>