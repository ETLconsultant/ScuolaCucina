<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="Header.jsp"%>
<link rel="stylesheet" type="text/css" href="css/form.css">
</head>
<body>
<div align="center">
	<form action="regUtente">

		<h3>REGISTRAZIONE UTENTE</h3> <br>
		<h4>Username</h4> <br>
		<input type="text" name="idUtente">
		<c:forEach items="${lista}" var="errore">
			<c:if test="${errore.campoValidato=='idUtente'}"> ${errore.descrizioneErrore}</c:if>
		</c:forEach>
<br>

		<h4>Password</h4> <br>
		<input type="text" name="password">
		<c:forEach items="${lista}" var="errore">
			<c:if test="${errore.campoValidato=='password'}"> ${errore.descrizioneErrore}</c:if>
		</c:forEach>
<br>

		<h4>Nome</h4> <br>
		<input type="text" name="nome">
		<c:forEach items="${lista}" var="errore">
			<c:if test="${errore.campoValidato=='nome'}"> ${errore.descrizioneErrore}</c:if>
		</c:forEach>
<br>
		<h4>Cognome</h4><br>
		 <input type="text" name="cognome">
		<c:forEach items="${lista}" var="errore">
			<c:if test="${errore.campoValidato=='cognome'}"> ${errore.descrizioneErrore}</c:if>
		</c:forEach>
<br>
		<h4>Giorno di nascita: </h4><select name="giorno">
			<c:forEach begin="1" end="31" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
		</select><br>
		<h4> Mese di nascita: </h4><select name="mese">
			<c:forEach begin="1" end="12" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
		</select> <br>
		<h4>Anno di nascita</h4><br>
		<input type="text" name="anno">
		<c:forEach items="${lista}" var="errore">
			<c:if test="${errore.campoValidato=='anno'}"> ${errore.descrizioneErrore}</c:if>
		</c:forEach>
<br>
		<h4>EMail</h4><br>
		 <input type="text" name="email">
		<c:forEach items="${lista}" var="errore">
			<c:if test="${errore.campoValidato=='email'}"> ${errore.descrizioneErrore}</c:if>
		</c:forEach>
<br>

		<h4>Telefono</h4> 
		<br>
		<input type="text" name="telefono">
		<c:forEach items="${lista}" var="errore">
			<c:if test="${errore.campoValidato=='telefono'}"> ${errore.descrizioneErrore}</c:if>
		</c:forEach>
		<br> <br> <br> <input type="submit" value="registra"
			class="button"><br>

	</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>

