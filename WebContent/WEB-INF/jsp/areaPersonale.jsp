<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="ISO-8859-1">
<%@include file="Header.jsp"%>
<title>Area Personale</title>
<%String username1 = (String) session.getAttribute("idUtente"); %>
<link rel="stylesheet" type="text/css" href="css/form.css">
</head>
<body>
 
	<p>
		Ciao
		<%=username1%>, da qui puoi modificare i tuoi dati o cancellare il tuo
		account. Seleziona l'operazione che vuoi effettuare:
		
		<button class="button">
			<a href="updateDati.jsp" id="dati"> Modifica Dati</a>
		</button>


		<form action="CancellazioneUtenteServlet" method="post">

			<input type="submit" name="cancellare" value="Cancella account"
				class="button">
		</form>
	</p>
	<%@include file="Footer.jsp"%>
</body>
</html>