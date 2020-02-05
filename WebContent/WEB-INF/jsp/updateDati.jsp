<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="ISO-8859-1">
<%@include file="Header.jsp"%>
<link rel="stylesheet" type="text/css" href="css/form.css">
<script type="text/javascript" src="js/gestioneForm.js"></script>
<title>Aggiorna dati</title>
</head>
<body>
<div align="center"> 

<p Id="mex"></p>
<form name="myForm" action="Update" method="post"  onsubmit="fieldValidation()">

<p>Username: </p>
<input type ="text" name = "username" size = "20px"  >

<p>Nome: </p>
<input type ="text" name = "nome" size = "20px"  >

<p>Cognome: </p>
<input type ="text" name = "cognome" size = "20px"  >

<p>Password:</p>
<input type ="password" name = "password" size = "20px" >

<br> <br> <br>
<input type = "submit" name = "update" value ="Aggiorna Dati" class="button">

</form>
</div>
	<%@include file="Footer.jsp"%>
</body>
</html>