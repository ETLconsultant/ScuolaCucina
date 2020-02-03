<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="Header.jsp"%>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/form.css" type="text/css">
</head>
<body>
	<div align="center"> 

		<h2>Benvenuto! Inserisci Username e Password</h2>
		<form name="myForm" action="Login" method="post">

			<h3>Utente</h3>
			<input type="text" name="username" size="20 px">

			<h3>Password</h3>
			<input type="password" name="password" size="20 px"> <br>
			<br> <input type="hidden" name="nome" value="nome" size="20 px">
			<input type="hidden" name="cognome" value="cognome" size="20 px">

			<input type="submit" name="submit" value="Invia" class="button">
		</form>

		<br> <br> <br>

		<h3>
			Non sei ancora registrato? <a href="registraUtente.jsp" class="reg">Clicca qui! </a>
		</h3>

	</div>

	<%@include file="Footer.jsp"%>
</body>
</html>