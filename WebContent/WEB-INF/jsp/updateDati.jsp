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
	<%
		String messaggio = (String) request.getAttribute("msg");
		if (messaggio != null) {
			out.println(messaggio);
		}
	%>
	<div align="center"> 

<p Id="mex"></p>
<form name="myForm" action="UpdateUtenteServlet" method="post"  onsubmit="fieldValidation()">

<input type="hidden" name="username" value="username">
					

<p>Nome: </p>
<input type ="text" name = "nome" size = "20px"  >

<p>Cognome: </p>
<input type ="text" name = "cognome" size = "20px"  >

<p>Data di Nascita: </p>
<input type ="date" name = "dataNascita" size = "20px"  >

<p>Email: </p>
<input type ="text" name = "email" size = "20px"  >

<p>Telefono: </p>
<input type ="text" name = "telefono" size = "20px"  >

<p>Password:</p>
<input type ="password" name = "password" size = "20px" >

<br> <br> <br>
<input type = "submit" name = "update" value ="Aggiorna Dati" class="button">

</form>
</div>
	<%@include file="Footer.jsp"%>
</body>
</html>