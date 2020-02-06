<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="Header.jsp"%>
<%String username1 = (String) session.getAttribute("username"); %>
<link rel="stylesheet" type="text/css" href="css/areaP.css">
<title>Area Personale Admin</title>
</head>
<body>
<div class="wrap">
	<p>
		Ciao
		<%=username1%>, da qui puoi modificare i tuoi dati: </p>
		<br>
		<button class="button1" >
			<a href="updateDati.jsp" id="dati"> Modifica Dati</a>
		</button>

		<br> <br> 

	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>