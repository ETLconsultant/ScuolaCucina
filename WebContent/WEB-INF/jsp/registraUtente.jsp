<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="Header.jsp"%>
 
<link rel="stylesheet" type="text/css" href="css/form.css">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
</head>
<body>
	<div align="center">
		<form action="regUtente">

			<h3>REGISTRAZIONE UTENTE</h3>
			<br>
			<h4>Username</h4>
			<br> <input type="text" name="username">
			<c:forEach items="${lista}" var="errore">
				<c:if test="${errore.campoValidato=='idUtente'}"> ${errore.descrizioneErrore}</c:if>
			</c:forEach>
			<br> 

			<h4>Password</h4>
			<br> <input type="text" name="password">
			<c:forEach items="${lista}" var="errore">
				<c:if test="${errore.campoValidato=='password'}"> ${errore.descrizioneErrore}</c:if>
			</c:forEach>
			<br>

			<h4>Nome</h4>
			<br> <input type="text" name="nome">
			<c:forEach items="${lista}" var="errore">
				<c:if test="${errore.campoValidato=='nome'}"> ${errore.descrizioneErrore}</c:if>
			</c:forEach>
			<br>
			<h4>Cognome</h4>
			<br> <input type="text" name="cognome">
			<c:forEach items="${lista}" var="errore">
				<c:if test="${errore.campoValidato=='cognome'}"> ${errore.descrizioneErrore}</c:if>
			</c:forEach>
			<br>
			<h4>Data di nascita</h4>
			<input type="date" name="dataNascita" onclick="fieldValidationDate()">
			<c:forEach items="${lista}" var="errore">
				<c:if test="${errore.campoValidato=='dataNascita'}"> ${errore.descrizioneErrore}</c:if>
			</c:forEach> <br>
			<h4>EMail</h4>
			<br> <input type="text" name="email">
			<c:forEach items="${lista}" var="errore">
				<c:if test="${errore.campoValidato=='email'}"> ${errore.descrizioneErrore}</c:if>
			</c:forEach>
			<br>
			<!--<h4>EMail</h4>
			<br> <input type="text" name="email">
			<c:forEach items="${lista}" var="errore">
				<c:if test="${errore.campoValidato=='email'}"> ${errore.descrizioneErrore}</c:if>
			</c:forEach> 
			<br>-->

			<h4>Telefono</h4>
			<br> <input type="text" name="telefono">
			<!-- <c:forEach items="${lista}" var="errore">
				<c:if test="${errore.campoValidato=='telefono'}"> ${errore.descrizioneErrore}</c:if>
			</c:forEach>--> <br>
			<h4>Sei un amministratore?</h4>
			<label> <input type="checkbox" class="radio" value="true"
				name="admin" />Si
			</label> <label> <input type="checkbox" class="radio" value="false"
				name="admin" />No
			</label> <br> <br> <br> <input type="submit" value="registra"
				class="button"><br>

		</form>
	</div>

	<script>
		$("input:checkbox").on('click', function() {
			// in the handler, 'this' refers to the box clicked on
			var $box = $(this);
			if ($box.is(":checked")) {
				// the name of the box is retrieved using the .attr() method
				// as it is assumed and expected to be immutable
				var group = "input:checkbox[name='" + $box.attr("name") + "']";
				// the checked state of the group/box on the other hand will change
				// and the current value is retrieved using .prop() method
				$(group).prop("checked", false);
				$box.prop("checked", true);
			} else {
				$box.prop("checked", false);
			}
		});
	</script>
	<%@include file="Footer.jsp"%>
</body>
</html>

